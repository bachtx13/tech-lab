package com.sem4project.sem4.controller.impl;


import com.sem4project.sem4.controller.BaseController;
import com.sem4project.sem4.dto.dtomodel.CategoryDto;
import com.sem4project.sem4.dto.response.ResponseObject;
import com.sem4project.sem4.entity.Category;
import com.sem4project.sem4.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Category", description = "Category API")
@RestController
@AllArgsConstructor
@RequestMapping("/category")
@CrossOrigin
public class CategoryController extends BaseController<Category, CategoryDto> {
    private final CategoryService categoryService;

    @Override
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    protected ResponseEntity<ResponseObject> getAll(@RequestParam(required = false, defaultValue = "false") Boolean isDisable,
                                                    @RequestParam(defaultValue = "1") Integer pageNumber,
                                                    @RequestParam(required = false) Integer pageSize,
                                                    @RequestParam(required = false) String sortBy,
                                                    @RequestParam(required = false) String sortType
    ) {
        return ResponseEntity.status(200)
                .body(
                        ResponseObject.builder()
                                .message("Success")
                                .data(categoryService.getAll(isDisable, pageNumber, pageSize, sortBy, sortType))
                                .build()
                );
    }
    @RequestMapping(value = "/getAllAvailable", method = RequestMethod.GET)
    protected ResponseEntity<ResponseObject> getAllAvailable(@RequestParam(required = false, defaultValue = "false") Boolean isDisable,
                                                    @RequestParam(defaultValue = "1") Integer pageNumber,
                                                    @RequestParam(required = false) Integer pageSize,
                                                    @RequestParam(required = false) String sortBy,
                                                    @RequestParam(required = false) String sortType
    ) {
        return ResponseEntity.status(200)
                .body(
                        ResponseObject.builder()
                                .message("Success")
                                .data(categoryService.getAllAvailable(pageNumber, pageSize, sortBy, sortType))
                                .build()
                );
    }

    @Override
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    protected ResponseEntity<ResponseObject> getById(@PathVariable UUID id) {
        return ResponseEntity.status(200)
                .body(
                        ResponseObject.builder()
                                .message("Success")
                                .data(categoryService.getById(id))
                                .build()
                );
    }

    @Override
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @PostAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    protected ResponseEntity<ResponseObject> create(@RequestBody CategoryDto categoryDto) {
        return ResponseEntity.status(201)
                .body(
                        ResponseObject.builder()
                                .message("Created")
                                .data(categoryService.create(categoryDto))
                                .build()
                );
    }

    @Override
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PATCH)
    @PostAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    protected ResponseEntity<ResponseObject> update(UUID id, CategoryDto categoryDto) {
        return ResponseEntity.status(200)
                .body(
                        ResponseObject.builder()
                                .message("Success")
                                .data(categoryService.update(id, categoryDto))
                                .build()
                );
    }

    @Override
    @PostAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    protected ResponseEntity<ResponseObject> updateDisable(@PathVariable UUID id) {
        categoryService.updateDisable(id);
        return ResponseEntity.status(200)
                .body(
                        ResponseObject.builder()
                                .message("Success")
                                .data(true)
                                .build()
                );
    }

    @Override
    protected ResponseEntity<ResponseObject> getAllAvailable(Integer pageNumber, Integer pageSize, String sortBy, String sortType) {
        return null;
    }
}
