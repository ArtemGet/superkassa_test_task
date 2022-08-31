package com.company.task.combination;

import java.util.List;

public interface CombinationResolverAdapter<T extends Mergeable<?>> {
    List<T> resolveCombinations(List<? extends T> toMerge);
}
