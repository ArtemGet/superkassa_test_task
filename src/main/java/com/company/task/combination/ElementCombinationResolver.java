package com.company.task.combination;

import com.company.task.element.Element;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ElementCombinationResolver<T> implements CombinationResolver<Element<T>> {
    @Override
    public Set<Element<T>> resolveCombinations(List<? extends Element<T>> toMerge) {
        Set<Element<T>> result = new LinkedHashSet<>();
        for (int i = 0; i < toMerge.size(); i++) {
            Element<T> currentElement = toMerge.get(i);

            if (currentElement.isNullFree) {
                result.add(currentElement);
            } else {
                this.searchForCombinations(i + 1, currentElement, toMerge, result);
            }
        }

        return result;
    }

    private void searchForCombinations(int currentPos,
                                       Element<T> currentElement,
                                       List<? extends Element<T>> toMerge,
                                       Set<Element<T>> result) {
        for (int j = currentPos; j < toMerge.size(); j++) {
            Element<T> nextElement = toMerge.get(j);
            Element<T> mergeResult = currentElement.merge(nextElement);

            if (mergeResult != null && mergeResult.isConsistent) {
                result.add(mergeResult);
            } else if (mergeResult != null) {
                for (int k = j + 1; k < toMerge.size(); k++) {
                    this.searchForCombinations(currentPos + 1, mergeResult, toMerge, result);
                }
            }
        }
    }
}
