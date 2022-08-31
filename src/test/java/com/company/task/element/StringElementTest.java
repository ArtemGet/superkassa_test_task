package com.company.task.element;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class StringElementTest {

    @Test
    public void merge_shouldMergeElements_whenHavingNullAndValueInSamePosition() {
        Element<String> first = new Element<>(Arrays.asList("1", null, null, "4"));
        Element<String> second = new Element<>(Arrays.asList(null, "2", "3", null));

        Element<String> expected = new Element<>(Arrays.asList("1", "2", "3", "4"));
        Element<String> actual = first.merge(second);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void merge_shouldReturnNull_whenDifferentSize() {
        Element<String> first = new Element<>(Arrays.asList("1"));
        Element<String> second = new Element<>(Arrays.asList("1", null));

        Assertions.assertNull(first.merge(second));
    }

    @Test
    public void merge_shouldMergeElementsAndSetInconsistentState_whenHaveNullInOnePlace() {
        Element<String> first = new Element<>(Arrays.asList("1", null));
        Element<String> second = new Element<>(Arrays.asList(null, null));

        Element<String> expected = new Element<>(Arrays.asList("1", null), false);

        Assertions.assertEquals(expected, first.merge(second));
        Assertions.assertFalse(expected.isConsistent);
    }

    @Test
    public void merge_shouldReturnNull_whenBothElementsHaveValueInOnePlace() {
        Element<String> first = new Element<>(Arrays.asList("1", "2"));
        Element<String> second = new Element<>(Arrays.asList("1", null));

        Assertions.assertNull(first.merge(second));
    }
}