package com.sem4project.sem4.service;

import com.sem4project.sem4.dto.dtomodel.BaseDto;
import com.sem4project.sem4.dto.dtomodel.BrandDto;
import com.sem4project.sem4.entity.BaseEntity;

import java.util.List;
import java.util.UUID;

public interface BaseService<T extends BaseEntity, U extends BaseDto> {
    U getById(UUID id);
    List<U> getAll(Boolean isDisable, Integer pageNumber, Integer pageSize, String sortBy, String sortType);
    List<U> getAllAvailable(Integer pageNumber, Integer pageSize, String sortBy, String sortType);
    U create(U u);
    U update(UUID id, U u);
    void updateDisable(UUID id);
    Long count(Boolean isDisable);
}
