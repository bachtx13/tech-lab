package com.sem4project.sem4.mapper;

import com.sem4project.sem4.dto.dtomodel.BaseDto;
import com.sem4project.sem4.entity.BaseEntity;

import java.util.Collection;
import java.util.List;

public interface BaseMapper<T extends BaseEntity, U extends BaseDto> {
    U toDto(T t);
    List<U> toListDto(List<T> ts);
    T toEntity(U u);
    List<T> toListEntity(List<U> us);
    void transferToDto(U u, T t);
    void transferToEntity(T t, U u);
}
