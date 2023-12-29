package com.sem4project.sem4.service.utils;

import com.sem4project.sem4.entity.BaseEntity;
import com.sem4project.sem4.util.PageableUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ServiceUtil {
    public static <T extends BaseEntity> List<T> getAllAvailable(
            Function<Boolean, Long> count,
            Integer pageNumber,
            Integer pageSize,
            String sortBy,
            String sortType,
            Function<Sort, List<T>> getBySort,
            Function<Pageable, Page<T>> getByPage
    ) {
        List<T> entities;
        if (pageSize == null) {
            Sort sort = PageableUtil.createSortFromString(sortBy, sortType);
            entities = getBySort.apply(sort);
        } else {
            Long quantity = count.apply(null);
            Pageable pageable = PageableUtil.calculatePageable(quantity, pageNumber, pageSize, sortBy, sortType);
            entities = getByPage.apply(pageable).stream().toList();
        }
        return entities;
    }

    public static <T extends BaseEntity> List<T> getAll(
            Function<Boolean, Long> count,
            Boolean isDisable,
            Integer pageNumber,
            Integer pageSize,
            String sortBy,
            String sortType,
            BiFunction<Boolean, Sort, List<T>> getBySort,
            BiFunction<Boolean, Pageable, List<T>> getByPage
    ) {
        List<T> entities;
        if (pageSize == null) {
            Sort sort = PageableUtil.createSortFromString(sortBy, sortType);
            entities = getBySort.apply(isDisable, sort);
        } else {
            Long quantity = count.apply(isDisable);
            Pageable pageable = PageableUtil.calculatePageable(quantity, pageNumber, pageSize, sortBy, sortType);
            entities = getByPage.apply(isDisable, pageable).stream().toList();
        }
        return entities;
    }
}
