package com.sem4project.sem4.controller.impl;

import com.sem4project.sem4.controller.BaseController;
import com.sem4project.sem4.dto.dtomodel.BrandDto;
import com.sem4project.sem4.dto.response.ResponseObject;
import com.sem4project.sem4.entity.Brand;
import com.sem4project.sem4.service.BrandService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Brand", description = "Brand API")
@RestController
@AllArgsConstructor
@RequestMapping("/brand")
@CrossOrigin
public class BrandController extends BaseController<Brand, BrandDto> {
    private final BrandService brandService;
    @Override
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<ResponseObject> create(@RequestBody BrandDto brandDto) {
        return ResponseEntity.status(201)
                .body(ResponseObject.builder()
                        .message("Created")
                        .data(brandService.create(brandDto))
                        .build()
                );
    }

    @Override
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<ResponseObject> getAll(@RequestParam(required = false, defaultValue = "false") Boolean isDisable,
                                                 @RequestParam(defaultValue = "1") Integer pageNumber,
                                                 @RequestParam(required = false) Integer pageSize,
                                                 @RequestParam(required = false) String sortBy,
                                                 @RequestParam(required = false) String sortType
    ) {
        return ResponseEntity.status(200)
                .body(
                        ResponseObject.builder()
                                .message("Success")
                                .data(brandService.getAll(isDisable, pageNumber, pageSize, sortBy, sortType))
                                .build()
                );
    }

    @Override
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseObject> getById(@PathVariable UUID id) {
        return ResponseEntity.status(200)
                .body(
                        ResponseObject.builder()
                                .message("Success")
                                .data(brandService.getById(id))
                                .build()
                );
    }

    @Override
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseObject> update(@PathVariable UUID id, @RequestParam BrandDto brandDto) {
        return ResponseEntity.status(200)
                .body(
                        ResponseObject.builder()
                                .message("Success")
                                .data(brandService.update(id, brandDto))
                                .build()
                );
    }

    @Override
    @RequestMapping(value = "/updateDisable/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<ResponseObject> updateDisable(@PathVariable UUID id) {
        brandService.updateDisable(id);
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

