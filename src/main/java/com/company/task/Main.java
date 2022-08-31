package com.company.task;

import com.company.task.combination.ElementCombinationResolver;
import com.company.task.combination.ElementCombinationResolverAdapter;
import com.company.task.element.ElementFactory;
import com.company.task.element.ElementMapper;
import com.company.task.service.ElementCombinationFacade;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;

public class Main {

    //пример использования апи
    public static void main(String[] args) {
        ElementCombinationFacade<String> stringElementCombinationFacade =
                new ElementCombinationFacade<>(
                        new ElementCombinationResolverAdapter<>(
                                new ElementCombinationResolver<>()),
                        new ElementMapper<>(
                                new ElementFactory<>()),
                        new ObjectMapper()
                );

        List<List<String>> combinations = stringElementCombinationFacade
                .resolveCombinationForFile(new File("src/main/resources/test.json"));

        System.out.println(combinations);
    }
}