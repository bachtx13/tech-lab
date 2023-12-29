package com.sem4project.sem4.controller.impl;

import com.sem4project.sem4.controller.BaseController;
import com.sem4project.sem4.dto.dtomodel.RoleDto;
import com.sem4project.sem4.dto.response.ResponseObject;
import com.sem4project.sem4.entity.Role;
import com.sem4project.sem4.service.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Brand", description = "Brand API")
@RestController
@AllArgsConstructor
@RequestMapping("/role")
@CrossOrigin
public class RoleController extends BaseController<Role, RoleDto> {
    private final RoleService roleService;
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @Override
    protected ResponseEntity<ResponseObject> getAll(@RequestParam(required = false, defaultValue = "false") Boolean isDisable,
                                                    @RequestParam(defaultValue = "1") Integer pageNumber,
                                                    @RequestParam(defaultValue = "10") Integer pageSize,
                                                    @RequestParam(required = false, defaultValue = "updatedAt") String sortBy,
                                                    @RequestParam(required = false, defaultValue = "ASC") String sortType) {
        return ResponseEntity.status(200)
                .body(
                        ResponseObject.builder()
                                .message("Success")
                                .data(roleService.getAll(isDisable, pageNumber, pageSize, sortBy, sortType))
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
                                .data(roleService.getById(id))
                                .build()
                );
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @Override
    protected ResponseEntity<ResponseObject> create(RoleDto roleDto) {
        return ResponseEntity.status(201)
                .body(
                        ResponseObject.builder()
                                .message("Created")
                                .data(roleService.create(roleDto))
                                .build()
                );
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    @Override
    protected ResponseEntity<ResponseObject> update(UUID id, RoleDto roleDto) {
        return ResponseEntity.status(200)
                .body(
                        ResponseObject.builder()
                                .message("Success")
                                .data(roleService.update(id, roleDto))
                                .build()
                );
    }

    @RequestMapping(value = "/updateDisable/{id}", method = RequestMethod.PATCH)
    @Override
    protected ResponseEntity<ResponseObject> updateDisable(@PathVariable UUID id) {
        roleService.updateDisable(id);
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
