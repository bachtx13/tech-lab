package com.sem4project.sem4.service.impl;

import com.sem4project.sem4.dto.dtomodel.*;
import com.sem4project.sem4.entity.Product;
import com.sem4project.sem4.exception.ResourceNotFoundException;
import com.sem4project.sem4.exception.UpdateResourceException;
import com.sem4project.sem4.mapper.*;
import com.sem4project.sem4.repository.CategoryRepository;
import com.sem4project.sem4.repository.ProductRepository;
import com.sem4project.sem4.service.ProductService;
import com.sem4project.sem4.service.utils.ServiceUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper = ProductMapper.INSTANCE;
    private final PhotoMapper photoMapper = PhotoMapper.INSTANCE;
    private final GiftMapper giftMapper = GiftMapper.INSTANCE;
    private final SpecificationMapper specificationMapper = SpecificationMapper.INSTANCE;
    private final PostMapper postMapper = PostMapper.INSTANCE;
    private final CategoryMapper categoryMapper = CategoryMapper.INSTANCE;
    private final BrandMapper brandMapper = BrandMapper.INSTANCE;
    private final RateMapper rateMapper = RateMapper.INSTANCE;
    private final BranchProductMapper branchProductMapper = BranchProductMapper.INSTANCE;
    private final CommentMapper commentMapper = CommentMapper.INSTANCE;

    @Override
    public ProductDto getById(UUID id) {
        try {
            Product product = productRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            ProductDto productDto = productMapper.toDto(product);
            productDto.setSpecifications(specificationMapper.toListDto(product.getSpecifications()));
            productDto.setPhotos(photoMapper.toListDto(product.getPhotos()));
            productDto.setBrand(brandMapper.toDto(product.getBrand()));
            productDto.setGifts(giftMapper.toListDto(product.getGifts()));
            productDto.setComments(commentMapper.toListDto(product.getComments()));
            productDto.setCategories(categoryMapper.toListDto(product.getCategories()));
            return productDto;
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("Product with id = " + id + " not found");
        }
    }

    @Override
    public List<ProductDto> getAll(Boolean isDisable, Integer pageNumber, Integer pageSize, String sortBy, String sortType) {
        try {
            if (isDisable != null && isDisable) {
                return getAllAvailable(pageNumber, pageSize, sortBy, sortType);
            }
            List<Product> products = ServiceUtil.getAll(this::count, isDisable, pageNumber, pageSize, sortBy, sortType, productRepository::findAllByDisable, productRepository::findAllByDisable);

            return products.stream().map(productMapper::toDto).toList();
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("Get products failed");
        }
    }

    @Override
    public List<ProductDto> getAllAvailable(Integer pageNumber, Integer pageSize, String sortBy, String sortType) {
        try {
            List<Product> products = ServiceUtil.getAllAvailable(this::count, pageNumber, pageSize, sortBy, sortType, productRepository::findAll, productRepository::findAll);
            return productMapper.toListDto(products);
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("Get products failed");
        }
    }

    @Override
    public ProductDto create(ProductDto productDto) {
        try {
            Product product = productMapper.toEntity(productDto);
            String productCode = generateProductCode(productDto.getTitle());
            product.setProductCode(productCode);
            transferExtendPropertiesToEntity(product, productDto);
            Product createdProduct = productRepository.save(product);
//            productRepository.refresh(createdProduct);
            return productMapper.toDto(createdProduct);
        } catch (OptimisticEntityLockException e) {
            throw new UpdateResourceException("Can not create new product");
        }
    }

    @Override
    public ProductDto update(UUID id, ProductDto productDto) {
        try {
            Product product = productRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            productMapper.transferToEntity(product, productDto);
            transferExtendPropertiesToEntity(product, productDto);
            Product updatedProduct = productRepository.save(product);
//            productRepository.refresh(updatedProduct);
            return productMapper.toDto(updatedProduct);
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("Product with id = " + id + " not found");
        } catch (OptimisticEntityLockException ex) {
            throw new UpdateResourceException("Update product failed");
        }
    }

    @Override
    public void updateDisable(UUID id) {
        try {
            Product product = productRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            product.setDisable(!product.isDisable());
            productRepository.save(product);
        } catch (IllegalArgumentException | EntityNotFoundException ex) {
            throw new ResourceNotFoundException("Product with id = " + id + " not found");
        } catch (OptimisticEntityLockException e) {
            throw new UpdateResourceException("Can not update product");
        }
    }

    @Override
    public Long count(Boolean isDisable) {
        if(isDisable == null){
            return categoryRepository.count();
        }
        return categoryRepository.countByDisable(isDisable);
    }

    private String generateProductCode(String title) {
        long timestamp = new Date().getTime();

        return Arrays.stream(title.split(" ")).reduce("", (result, word) -> result + word.charAt(0)).toUpperCase() + Long.toString(timestamp).substring(Long.toString(timestamp).length() - 4);
    }

    private void transferExtendPropertiesToEntity(Product product, ProductDto productDto){

        BrandDto brandDto = productDto.getBrand();
        PostDto postDto = productDto.getPost();
        List<PhotoDto> photoDtoList = productDto.getPhotos();
        List<GiftDto> giftDtoList = productDto.getGifts();
        List<SpecificationDto> specificationDtoList = productDto.getSpecifications();
        List<CategoryDto> categoryDtoList = productDto.getCategories();
        if (brandDto != null) {
            product.setBrand(brandMapper.toEntity(brandDto));
        }
        if (postDto != null) {
            product.setPost(postMapper.toEntity(postDto));
        }
        if (photoDtoList != null && !photoDtoList.isEmpty()) {
            product.setPhotos(photoMapper.toListEntity(photoDtoList));
        }
        if (giftDtoList != null && !giftDtoList.isEmpty()) {
            product.setGifts(giftMapper.toListEntity(giftDtoList));
        }
        if (specificationDtoList != null && !specificationDtoList.isEmpty()) {
            product.setSpecifications(specificationMapper.toListEntity(specificationDtoList));
        }
        if (categoryDtoList != null && !categoryDtoList.isEmpty()) {
            Boolean categoriesAreExists = categoryRepository.existsAllByIdIn(categoryDtoList.stream().map(CategoryDto::getId).toList());
            if (!categoriesAreExists){
                throw new ResourceNotFoundException("Category is not exists");
            }
            product.setCategories(categoryMapper.toListEntity(categoryDtoList));
        }
    }

    @Override
    public List<ProductDto> getAllByCost(double min, double max) {
        List<Product> products = productRepository.findAllByCostBetweenAndDisableFalse(min, max);
        return productMapper.toListDto(products);
    }



    @Override
    public List<ProductDto> sortedByCost(String sortType) {
        try{
            List<Product> products = productRepository.findAllByDisableFalse();
            if (sortType.equalsIgnoreCase("ASC")){
                products.sort(Comparator.comparingDouble(Product::getCost));
            } else if (sortType.equalsIgnoreCase("DESC")){
                products.sort(Comparator.comparingDouble(Product::getCost));
            } else {
                throw new IllegalArgumentException("Invalid sort type:" + sortType);
            }
            return productMapper.toListDto(products);
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Get products failed");
        }
    }
}
