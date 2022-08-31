package com.company.task.service;

import com.company.task.combination.CombinationResolverAdapter;
import com.company.task.element.Element;
import com.company.task.element.ElementMapper;
import com.company.task.exception.UnableToMapFileException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ElementCombinationFacade<T> {
    private final CombinationResolverAdapter<Element<T>> combinationResolver;
    private final ElementMapper<T> elementMapper;
    private final ObjectMapper objectMapper;

    public ElementCombinationFacade(CombinationResolverAdapter<Element<T>> combinationResolver,
                                    ElementMapper<T> elementMapper,
                                    ObjectMapper objectMapper) {
        this.combinationResolver = combinationResolver;
        this.elementMapper = elementMapper;
        this.objectMapper = objectMapper;
    }

    public List<List<T>> resolveCombinationForFile(File file) {
        try {
            List<List<T>> dto = objectMapper.readValue(file, new TypeReference<>() {});
            List<Element<T>> elements = elementMapper.mapFromDTOList(dto);

            List<Element<T>> combinationResult = combinationResolver.resolveCombinations(elements);
            return elementMapper.mapFromEntityList(combinationResult);
        } catch (IOException e) {
            throw new UnableToMapFileException(
                    String.format("unable to read/map file for path: %s", file.getPath()),
                    e);
        }
    }
}
