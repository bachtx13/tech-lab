package com.sem4project.sem4.controller.impl;

import com.sem4project.sem4.controller.BaseController;
import com.sem4project.sem4.dto.dtomodel.ProductDto;
import com.sem4project.sem4.dto.response.ResponseObject;
import com.sem4project.sem4.entity.Product;
import com.sem4project.sem4.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Product", description = "Product API")
@RestController
@AllArgsConstructor
@RequestMapping("/product")
@CrossOrigin
public class ProductController extends BaseController<Product, ProductDto> {
    private final ProductService productService;

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
                                .data(productService.getAll(isDisable, pageNumber, pageSize, sortBy, sortType))
                                .build()
                );
    }

    @RequestMapping(value = "/getAllAvailable", method = RequestMethod.GET)
    protected ResponseEntity<ResponseObject> getAllAvailable(@RequestParam(defaultValue = "1") Integer pageNumber,
                                                             @RequestParam(required = false) Integer pageSize,
                                                             @RequestParam(required = false) String sortBy,
                                                             @RequestParam(required = false) String sortType
    ) {
        return ResponseEntity.status(200)
                .body(
                        ResponseObject.builder()
                                .message("Success")
                                .data(productService.getAllAvailable(pageNumber, pageSize, sortBy, sortType))
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
                                .data(productService.getById(id))
                                .build()
                );
    }

    @Override
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @PostAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    protected ResponseEntity<ResponseObject> create(@Valid @RequestBody ProductDto productDto) {
        return ResponseEntity.status(201)
                .body(
                        ResponseObject.builder()
                                .message("Created")
                                .data(productService.create(productDto))
                                .build()
                );
    }

    @Override
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    @PostAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    protected ResponseEntity<ResponseObject> update(@PathVariable UUID id, @Valid @RequestBody ProductDto productDto) {
        return ResponseEntity.status(200)
                .body(
                        ResponseObject.builder()
                                .message("Success")
                                .data(productService.update(id, productDto))
                                .build()
                );
    }

    @Override
    @RequestMapping(value = "/updateDisable/{id}", method = RequestMethod.PATCH)
    @PostAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    protected ResponseEntity<ResponseObject> updateDisable(@PathVariable UUID id) {
        productService.updateDisable(id);
        return ResponseEntity.status(200)
                .body(
                        ResponseObject.builder()
                                .message("Success")
                                .data(true)
                                .build()
                );
    }

    @RequestMapping(value = "/getAllByCost", method = RequestMethod.GET)
    protected ResponseEntity<ResponseObject> getAllByCost(@RequestParam double min, @RequestParam double max) {

        return ResponseEntity.status(200)
                .body(
                        ResponseObject.builder()
                                .message("Success")
                                .data(productService.getAllByCost(min, max))
                                .build()
                );
    }

    @RequestMapping(value = "/sortedByCost", method = RequestMethod.GET)
    protected ResponseEntity<ResponseObject> sortedByCost(@RequestParam(name = "sortType", required = false) String sortType) {
        if (sortType == null) {
            sortType = "ASC";
        }
        return ResponseEntity.status(200)
                .body(
                        ResponseObject.builder()
                                .message("Success")
                                .data(productService.sortedByCost(sortType))
                                .build()
                );
    }


}
