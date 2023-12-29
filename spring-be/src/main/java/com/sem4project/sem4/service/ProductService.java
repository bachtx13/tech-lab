package com.sem4project.sem4.service;

import com.sem4project.sem4.dto.dtomodel.CategoryDto;
import com.sem4project.sem4.dto.dtomodel.ProductDto;
import com.sem4project.sem4.entity.Category;
import com.sem4project.sem4.entity.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService extends BaseService<Product, ProductDto> {
    List<ProductDto> getAllByCost(double min, double max);

    List<ProductDto> sortedByCost(String sortType);
}
