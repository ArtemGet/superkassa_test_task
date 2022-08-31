package com.company.task.element;

import java.util.Collections;
import java.util.List;

public class ElementFactory<T> {
    public Element<T> createElement(List<T> entries) {
        return new Element<>(Collections.unmodifiableList(entries));
    }
}
