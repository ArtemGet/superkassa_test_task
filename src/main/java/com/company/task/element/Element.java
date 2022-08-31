package com.company.task.element;

import com.company.task.combination.Mergeable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Element<T> implements Mergeable<Element<T>> {
    final List<T> entries;
    public final boolean isNullFree;
    public final boolean isConsistent;

    Element(List<T> entries) {
        this.entries = entries;
        this.isNullFree = !entries.contains(null);
        this.isConsistent = true;
    }

    Element(List<T> entries, boolean isConsistent) {
        this.entries = entries;
        this.isNullFree = !entries.contains(null);
        this.isConsistent = isConsistent;
    }

    @Override
    public Element<T> merge(Element<T> another) {
        if (this.entries.size() != another.entries.size()) {
            return null;
        }

        List<T> entries = new ArrayList<>();
        boolean isConsistent = true;
        for (int i = 0; i < this.entries.size(); i++) {
            if (this.entries.get(i) != null && another.entries.get(i) != null) {
                return null;
            }
            isConsistent = mergeEntries(
                    this.entries.get(i),
                    another.entries.get(i),
                    entries,
                    isConsistent);
        }

        return new Element<>(Collections.unmodifiableList(entries), isConsistent);
    }

    private boolean mergeEntries(T thisEntry, T anotherEntry, List<T> entries, boolean prevState) {
        if (thisEntry == null && anotherEntry != null) {
            entries.add(anotherEntry);
        } else if (thisEntry != null && anotherEntry == null) {
            entries.add(thisEntry);
        } else if (thisEntry == null) {
            entries.add(null);
            return false;
        }

        return prevState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Element)) return false;
        Element<?> element = (Element<?>) o;
        return isNullFree == element.isNullFree && entries.equals(element.entries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entries, isNullFree);
    }
}