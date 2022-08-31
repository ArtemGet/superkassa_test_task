package com.company.task;

import com.company.task.combination.ElementCombinationResolver;
import com.company.task.combination.ElementCombinationResolverAdapter;
import com.company.task.element.ElementFactory;
import com.company.task.element.ElementMapper;
import com.company.task.service.ElementCombinationFacade;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Main {

    //пример использования апи
    public static void main(String[] args) {

        List<List<String>> data = List.of(
                Arrays.asList("a1", "a2", "a3", "a4"),
                Arrays.asList("b1", null, null, "b4"),
                Arrays.asList(null, "c2", "c3", null),
                Arrays.asList("d1", null, null, "d4"),
                Arrays.asList(null, "e2", "e3", null),
                Arrays.asList(null, "f2", "f3", "f4"),
                Arrays.asList("h1", null, null, null),
                Arrays.asList("g1", null, null, null)
        );
        ElementCombinationFacade<String> stringElementCombinationFacade =
                new ElementCombinationFacade<>(
                        new ElementCombinationResolverAdapter<>(
                                new ElementCombinationResolver<>()),
                        new ElementMapper<>(
                                new ElementFactory<>()),
                        new ObjectMapper()
                );

        List<List<String>> combinations = stringElementCombinationFacade
                .resolveCombinationForFile(
                        new File("src/main/resources/test.json"),
                        new TypeReference<>() {});

        System.out.println(combinations);
    }
}