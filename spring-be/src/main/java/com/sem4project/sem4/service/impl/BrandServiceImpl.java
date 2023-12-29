package com.sem4project.sem4.service.impl;

import com.sem4project.sem4.dto.dtomodel.BrandDto;
import com.sem4project.sem4.entity.Brand;
import com.sem4project.sem4.entity.District;
import com.sem4project.sem4.exception.ResourceNotFoundException;
import com.sem4project.sem4.exception.UpdateResourceException;
import com.sem4project.sem4.mapper.BrandMapper;
import com.sem4project.sem4.repository.BrandRepository;
import com.sem4project.sem4.service.BrandService;
import com.sem4project.sem4.service.utils.ServiceUtil;
import com.sem4project.sem4.util.PageableUtil;
import lombok.AllArgsConstructor;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper = BrandMapper.INSTANCE;

    @Override
    public BrandDto getById(UUID id) {
        try {
            Brand brand = brandRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            return brandMapper.toDto(brand);
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("Brand with id = " + id + " not found");
        }
    }

    @Override
    public List<BrandDto> getAll(Boolean isDisable, Integer pageNumber, Integer pageSize, String sortBy, String sortType) {
        try {
            if(isDisable != null && isDisable){
                return getAllAvailable(pageNumber, pageSize, sortBy, sortType);
            }
            List<Brand> brands = ServiceUtil.getAll(this::count, isDisable, pageNumber, pageSize, sortBy, sortType, brandRepository::findAllByDisable, brandRepository::findAllByDisable);

            return brands.stream().map(brandMapper::toDto).toList();
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("Get districts failed");
        }
    }

    @Override
    public List<BrandDto> getAllAvailable(Integer pageNumber, Integer pageSize, String sortBy, String sortType) {
        try {
            List<Brand> brands = ServiceUtil.getAllAvailable(this::count, pageNumber, pageSize, sortBy, sortType, brandRepository::findAll, brandRepository::findAll);
            return brandMapper.toListDto(brands);
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("Get districts failed");
        }
    }

    @Override
    public BrandDto create(BrandDto brandDto) {
        try {
            Brand brand = brandMapper.toEntity(brandDto);
            Brand createdBrand = brandRepository.save(brand);
//            brandRepository.refresh(createdBrand);
            return brandMapper.toDto(createdBrand);
        } catch (OptimisticEntityLockException e) {
            throw new UpdateResourceException("Can not create new brand");
        }
    }

    @Override
    public BrandDto update(UUID id, BrandDto brandDto) {
        try {
            Brand brand = brandRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            brandMapper.transferToEntity(brand, brandDto);
            Brand updatedBrand = brandRepository.save(brand);
//            brandRepository.refresh(updatedBrand);
            return brandMapper.toDto(updatedBrand);
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("Brand with id = " + id + " not found");
        } catch (OptimisticEntityLockException ex) {
            throw new UpdateResourceException("Can not update brand");
        }
    }

    @Override
    public void updateDisable(UUID id) {
        try {
            Brand brand = brandRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            brand.setDisable(!brand.isDisable());
            brandRepository.save(brand);
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("Brand with id = " + id + " not found");
        } catch (OptimisticEntityLockException ex) {
            throw new UpdateResourceException("Can not update brand");
        }
    }

    @Override
    public Long count(Boolean isDisable) {
        if(isDisable == null){
            return brandRepository.count();
        }
        return brandRepository.countByDisable(isDisable);
    }
}
