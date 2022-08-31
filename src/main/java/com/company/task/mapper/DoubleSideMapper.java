package com.company.task.mapper;

import java.util.List;

public interface DoubleSideMapper<T, D> {
    T mapFromDTO(D dto);

    List<T> mapFromDTOList(List<D> dto);

    D mapFromEntity(T entity);

    List<D> mapFromEntityList(List<T> entities);
}
