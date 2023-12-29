package com.sem4project.sem4.controller;

import com.sem4project.sem4.dto.dtomodel.BaseDto;
import com.sem4project.sem4.dto.response.ResponseObject;
import com.sem4project.sem4.entity.BaseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

public abstract class BaseController<T extends BaseEntity, U extends BaseDto> {
    protected abstract ResponseEntity<ResponseObject> getAll(Boolean isDisable, Integer pageNumber, Integer pageSize, String sortBy, String sortType);
    protected abstract ResponseEntity<ResponseObject> getById(UUID id);
    protected abstract ResponseEntity<ResponseObject> create(U u);
    protected abstract ResponseEntity<ResponseObject> update(UUID id, U u);
    protected abstract ResponseEntity<ResponseObject> updateDisable(UUID id);
    protected abstract ResponseEntity<ResponseObject> getAllAvailable(Integer pageNumber,Integer pageSize,String sortBy, String sortType);
}
