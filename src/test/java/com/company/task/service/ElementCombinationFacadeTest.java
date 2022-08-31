package com.company.task.service;

import com.company.task.combination.ElementCombinationResolver;
import com.company.task.combination.ElementCombinationResolverAdapter;
import com.company.task.element.ElementFactory;
import com.company.task.element.ElementMapper;
import com.company.task.exception.UnableToMapFileException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ElementCombinationFacadeTest {
    private static ElementCombinationFacade<String> stringElementCombinationFacade;

    @BeforeAll
    public static void configure() {
        stringElementCombinationFacade =
                new ElementCombinationFacade<>(
                        new ElementCombinationResolverAdapter<>(
                                new ElementCombinationResolver<>()),
                        new ElementMapper<>(
                                new ElementFactory<>()),
                        new ObjectMapper()
                );
    }

    @Test
    public void resolveCombinationForFile_shouldThrowEx_whenNoSuchFileFound() {
        Assertions.assertThrows(UnableToMapFileException.class, () ->
                stringElementCombinationFacade
                        .resolveCombinationForFile(new File("doesnotexist.json")));
    }

    @Test
    public void resolveCombinationForFile_shouldCombine_whenTestData() {
        List<List<String>> actual = stringElementCombinationFacade
                .resolveCombinationForFile(new File("src/main/resources/test.json"));
        List<List<String>> expected = Arrays.asList(
                Arrays.asList("a1", "a2", "a3", "a4"),
                Arrays.asList("b1", "c2", "c3", "b4"),
                Arrays.asList("b1", "e2", "e3", "b4"),
                Arrays.asList("d1", "c2", "c3", "d4"),
                Arrays.asList("d1", "e2", "e3", "d4"),
                Arrays.asList("h1", "f2", "f3", "f4"),
                Arrays.asList("g1", "f2", "f3", "f4")
        );

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void resolveCombinationForFile_shouldCombine_whenDifferentSizes() {
        List<List<String>> actual = stringElementCombinationFacade
                .resolveCombinationForFile(new File("src/main/resources/test_diff_size.json"));
        List<List<String>> expected = Arrays.asList(
                Arrays.asList("a1", "a2", "a3", "a4"),
                Arrays.asList("b1", "c2", "c3", "b4"),
                Arrays.asList("b1", "e2", "e3", "b4"),
                Arrays.asList("d1", "c2", "c3", "d4"),
                Arrays.asList("d1", "e2", "e3", "d4"),
                Arrays.asList("h1", "f2", "f3", "f4"),
                Arrays.asList("g1", "f2", "f3", "f4"),
                Arrays.asList("1", "2")
        );

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void resolveCombinationForFile_shouldReturnEmptyList_whenEmptyData() {
        List<List<String>> actual = stringElementCombinationFacade
                .resolveCombinationForFile(new File("src/main/resources/test_empty.json"));
        List<List<String>> expected = new ArrayList<>();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void resolveCombinationForFile_shouldCombineWithoutDuplicates_whenDuplicatesExists() {
        List<List<String>> actual = stringElementCombinationFacade
                .resolveCombinationForFile(new File("src/main/resources/test_duplicates.json"));
        List<List<String>> expected = Arrays.asList(
                Arrays.asList("a1", "a2", "a3", "a4"),
                Arrays.asList("b1", "c2", "c3", "b4"),
                Arrays.asList("b1", "e2", "e3", "b4"),
                Arrays.asList("d1", "c2", "c3", "d4"),
                Arrays.asList("d1", "e2", "e3", "d4"),
                Arrays.asList("h1", "f2", "f3", "f4"),
                Arrays.asList("g1", "f2", "f3", "f4")
        );

        Assertions.assertEquals(expected, actual);
    }

    @Test void resolveCombinationForFile_shouldCombine_whenManyDifferentStringsCombines() {
        List<List<String>> actual = stringElementCombinationFacade
                .resolveCombinationForFile(new File("src/main/resources/test_many_string_combination.json"));
        List<List<String>> expected = Arrays.asList(
                Arrays.asList("a1", "b2", "c3", "b4"),
                Arrays.asList("a1", "b2", "b3", "b4"),
                Arrays.asList("a1", "d2", "c3", "d4"),
                Arrays.asList("a1", "d2", "b3", "d4")
        );

        Assertions.assertEquals(expected, actual);
    }
}