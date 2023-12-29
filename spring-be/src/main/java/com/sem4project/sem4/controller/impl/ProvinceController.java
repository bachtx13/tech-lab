package com.sem4project.sem4.controller.impl;

import com.sem4project.sem4.controller.BaseController;
import com.sem4project.sem4.dto.dtomodel.ProvinceDto;
import com.sem4project.sem4.dto.response.ResponseObject;
import com.sem4project.sem4.entity.Province;
import com.sem4project.sem4.service.ProvinceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Province", description = "Province API")
@RestController
@AllArgsConstructor
@RequestMapping("/province")
@CrossOrigin
public class ProvinceController extends BaseController<Province, ProvinceDto> {
    private final ProvinceService provinceService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<ResponseObject> create(@RequestBody ProvinceDto provinceDto) {
        return ResponseEntity.status(201)
                .body(
                        ResponseObject.builder()
                                .message("Created")
                                .data(provinceService.create(provinceDto))
                                .build()
                );

    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<ResponseObject> getAll(@RequestParam(required = false, defaultValue = "false") Boolean isDisable,
                                                 @RequestParam(defaultValue = "1") Integer pageNumber,
                                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                                 @RequestParam(required = false, defaultValue = "updatedAt") String sortBy,
                                                 @RequestParam(required = false, defaultValue = "ASC") String sortType) {
        return ResponseEntity.status(200)
                .body(
                        ResponseObject.builder()
                                .message("Success")
                                .data(provinceService.getAll(isDisable, pageNumber, pageSize, sortBy, sortType))
                                .build()
                );

    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseObject> getById(@PathVariable UUID id) {
        return ResponseEntity.status(200)
                .body(
                        ResponseObject.builder()
                                .message("Success")
                                .data(provinceService.getById(id))
                                .build()
                );

    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<ResponseObject> update(@PathVariable UUID id, @RequestBody ProvinceDto provinceDto) {
        return ResponseEntity.status(200)
                .body(
                        ResponseObject.builder()
                                .message("Success")
                                .data(provinceService.update(id, provinceDto))
                                .build()
                );
    }

    @RequestMapping(value = "/updateDisable/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<ResponseObject> updateDisable(@PathVariable UUID id) {
        provinceService.updateDisable(id);
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
