package com.company.task.element;

import com.company.task.element.Element;
import com.company.task.element.ElementFactory;
import com.company.task.mapper.DoubleSideMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ElementMapper<T> implements DoubleSideMapper<Element<T>, List<T>> {
    private final ElementFactory<T> elementFactory;

    public ElementMapper(ElementFactory<T> elementFactory) {
        this.elementFactory = elementFactory;
    }

    @Override
    public Element<T> mapFromDTO(List<T> dto) {
        return elementFactory.createElement(dto);
    }

    @Override
    public List<Element<T>> mapFromDTOList(List<List<T>> dto) {
        return dto.stream()
                .map(elementFactory::createElement)
                .collect(Collectors.toList());
    }

    @Override
    public List<T> mapFromEntity(Element<T> element) {
        return element.entries;
    }

    @Override
    public List<List<T>> mapFromEntityList(List<Element<T>> elements) {
        return elements.stream()
                .map(element -> element.entries)
                .collect(Collectors.toList());
    }
}
