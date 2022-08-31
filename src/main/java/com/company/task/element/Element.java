package com.company.task.element;

import com.company.task.combination.Mergeable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Element<T> implements Mergeable<Element<T>> {
    final List<T> entries;
    public boolean isNullFree;

    Element(List<T> entries) {
        this.entries = entries;
        this.isNullFree = !entries.contains(null);
    }

    @Override
    public Element<T> merge(Element<T> another) {
        if (this.entries.size() != another.entries.size()) {
            return null;
        }

        List<T> entries = new ArrayList<>();
        for (int i = 0; i < this.entries.size(); i++) {
            if (this.entries.get(i) == null && another.entries.get(i) != null) {
                entries.add(another.entries.get(i));
            } else if (this.entries.get(i) != null && another.entries.get(i) == null) {
                entries.add(this.entries.get(i));
            } else {
                return null;
            }
        }

        return new Element<>(Collections.unmodifiableList(entries));
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