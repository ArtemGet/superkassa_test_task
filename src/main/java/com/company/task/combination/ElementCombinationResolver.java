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
                for (int j = i + 1; j < toMerge.size(); j++) {
                    Element<T> nextElement = toMerge.get(j);
                    Element<T> mergeResult = currentElement.merge(nextElement);

                    if (mergeResult != null) {
                        result.add(mergeResult);
                    }
                }
            }
        }

        return result;
    }
}
