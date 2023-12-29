package com.sem4project.sem4.controller.impl;

import com.sem4project.sem4.controller.BaseController;
import com.sem4project.sem4.dto.dtomodel.DistrictDto;
import com.sem4project.sem4.dto.response.ResponseObject;
import com.sem4project.sem4.entity.District;
import com.sem4project.sem4.service.DistrictService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "District", description = "District API")
@RestController
@AllArgsConstructor
@RequestMapping("/district")
@CrossOrigin
public class DistrictController extends BaseController<District, DistrictDto> {
    private final DistrictService districtService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<ResponseObject> create(@RequestBody DistrictDto districtDto) {
        return ResponseEntity.status(201)
                .body(ResponseObject.builder()
                        .message("Created")
                        .data(districtService.create(districtDto))
                        .build()
                );
    }

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
                                .data(districtService.getAll(isDisable, pageNumber, pageSize, sortBy, sortType))
                                .build()
                );
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseObject> getById(@PathVariable UUID id) {
        return ResponseEntity.status(200)
                .body(
                        ResponseObject.builder()
                                .message("Success")
                                .data(districtService.getById(id))
                                .build()
                );
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseObject> update(@PathVariable UUID id, @RequestParam DistrictDto districtDto) {
        return ResponseEntity.status(200)
                .body(
                        ResponseObject.builder()
                                .message("Success")
                                .data(districtService.update(id, districtDto))
                                .build()
                );
    }

    @RequestMapping(value = "/updateDisable/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<ResponseObject> updateDisable(@PathVariable UUID id) {
        districtService.updateDisable(id);
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

