package com.company.task.combination;

import com.company.task.element.Element;

import java.util.ArrayList;
import java.util.List;

public class ElementCombinationResolverAdapter<T> implements CombinationResolverAdapter<Element<T>> {
    private final CombinationResolver<Element<T>> combinationResolver;

    public ElementCombinationResolverAdapter(CombinationResolver<Element<T>> combinationResolver) {
        this.combinationResolver = combinationResolver;
    }

    @Override
    public List<Element<T>> resolveCombinations(List<? extends Element<T>> toMerge) {
        return new ArrayList<>(combinationResolver.resolveCombinations(toMerge));
    }
}
