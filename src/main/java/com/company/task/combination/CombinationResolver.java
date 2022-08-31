package com.company.task.combination;

import java.util.List;
import java.util.Set;

public interface CombinationResolver<T extends Mergeable<?>> {
    Set<T> resolveCombinations(List<? extends T> toMerge);
}
