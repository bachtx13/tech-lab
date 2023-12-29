package com.sem4project.sem4.service.impl;

import com.sem4project.sem4.dto.dtomodel.CategoryDto;
import com.sem4project.sem4.entity.Branch;
import com.sem4project.sem4.entity.Brand;
import com.sem4project.sem4.entity.Category;
import com.sem4project.sem4.entity.District;
import com.sem4project.sem4.exception.ResourceNotFoundException;
import com.sem4project.sem4.exception.UpdateResourceException;
import com.sem4project.sem4.mapper.CategoryMapper;
import com.sem4project.sem4.mapper.UserMapper;
import com.sem4project.sem4.repository.CategoryRepository;
import com.sem4project.sem4.service.CategoryService;
import com.sem4project.sem4.service.utils.ServiceUtil;
import lombok.AllArgsConstructor;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper = CategoryMapper.INSTANCE;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    @Override
    public CategoryDto getById(UUID id) {
        try {
            Category category = categoryRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            List<CategoryDto> categoriesDto = categoryMapper.toListDto(category.getCategories());
            CategoryDto categoryDto = categoryMapper.toDto(category);
            categoryDto.setCategories(categoriesDto);
            return categoryDto;
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("Category with id = " + id + " not found");
        }
    }

    @Override
    public List<CategoryDto> getAll(Boolean isDisable, Integer pageNumber, Integer pageSize, String sortBy, String sortType) {
        try {
            if(isDisable != null && isDisable){
                return getAllAvailable(pageNumber, pageSize, sortBy, sortType);
            }
            List<Category> categories = ServiceUtil.getAll(this::count, isDisable, pageNumber, pageSize, sortBy, sortType, categoryRepository::findAllByDisableAndParentCategoryNull, categoryRepository::findAllByDisableAndParentCategoryNull);

            return categories.stream().map(category -> {
                CategoryDto categoryDto = getChildrenCategory(category);
                categoryDto.setCreatedBy(userMapper.toDto(category.getCreatedBy()));
                categoryDto.setUpdatedBy(userMapper.toDto(category.getUpdatedBy()));
                return categoryDto;
            }).toList();
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("Get categories failed");
        }
    }

    @Override
    public List<CategoryDto> getAllAvailable(Integer pageNumber, Integer pageSize, String sortBy, String sortType) {
        try {
            List<Category> categories = ServiceUtil.getAllAvailable(this::count, pageNumber, pageSize, sortBy, sortType, categoryRepository::findAllByParentCategoryNull, categoryRepository::findAllByParentCategoryNull);
            return categoryMapper.toListDto(categories);
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("Get districts failed");
        }
    }

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        try {
            Category category = categoryMapper.toEntity(categoryDto);
            transferExtendPropertiesToEntity(category, categoryDto);
            Category createdCategory = categoryRepository.save(category);
//            categoryRepository.refresh(createdCategory);
            return categoryMapper.toDto(createdCategory);
        } catch (OptimisticEntityLockException e) {
            throw new UpdateResourceException("Can not create new category");
        }
    }

    @Override
    public CategoryDto update(UUID id, CategoryDto categoryDto) {
        try {
            Category category = categoryRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            categoryMapper.transferToEntity(category, categoryDto);
            transferExtendPropertiesToEntity(category, categoryDto);
            Category updatedCategory = categoryRepository.save(category);
//            categoryRepository.refresh(updatedCategory);
            return categoryMapper.toDto(updatedCategory);
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("District with id = " + id + " not found");
        } catch (OptimisticEntityLockException ex){
            throw new UpdateResourceException("Update category failed");
        }
    }

    @Override
    public void updateDisable(UUID id) {
        try {
            Category category = categoryRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            category.setDisable(!category.isDisable());
            categoryRepository.save(category);
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("Category with id = " + id + " not found");
        } catch (OptimisticEntityLockException ex){
            throw new UpdateResourceException("Create category failed");
        }
    }

    @Override
    public Long count(Boolean isDisable) {
        if(isDisable == null){
            return categoryRepository.count();
        }
        return categoryRepository.countByDisable(isDisable);
    }

    private void transferExtendPropertiesToEntity(Category category, CategoryDto categoryDto){
        CategoryDto parentCategoryDto = categoryDto.getParentCategory();
        if(parentCategoryDto != null){
            boolean parentIsExists = categoryRepository.existsById(parentCategoryDto.getId());
            if (!parentIsExists){
                throw new ResourceNotFoundException("Parent category is not exists");
            }
            Category parentCategory = categoryMapper.toEntity(parentCategoryDto);
            parentCategory.setId(parentCategoryDto.getId());
            category.setParentCategory(parentCategory);
        }
    }
    private CategoryDto getChildrenCategory(Category category){
        CategoryDto categoryDto = categoryMapper.toDto(category);
        if(!category.getCategories().isEmpty()){
            List<CategoryDto> childrenList = category.getCategories().stream().map(this::getChildrenCategory).toList();
            categoryDto.setCategories(childrenList);
        }
        return categoryDto;
    }


}
