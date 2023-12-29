package com.sem4project.sem4.service.impl;

import com.sem4project.sem4.dto.dtomodel.ProvinceDto;
import com.sem4project.sem4.entity.Province;
import com.sem4project.sem4.exception.UpdateResourceException;
import com.sem4project.sem4.exception.ResourceNotFoundException;
import com.sem4project.sem4.mapper.DistrictMapper;
import com.sem4project.sem4.mapper.ProvinceMapper;
import com.sem4project.sem4.repository.ProvinceRepository;
import com.sem4project.sem4.service.ProvinceService;
import com.sem4project.sem4.service.utils.ServiceUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ProvinceServiceImpl implements ProvinceService {

    private final ProvinceRepository provinceRepository;
    private final ProvinceMapper provinceMapper = ProvinceMapper.INSTANCE;
    private final DistrictMapper districtMapper = DistrictMapper.INSTANCE;

    @Override
    public ProvinceDto create(ProvinceDto provinceDto) {
        try {
            Province province = provinceMapper.toEntity(provinceDto);
            Province createdProvince = provinceRepository.save(province);
//            provinceRepository.refresh(createdProvince);
            return provinceMapper.toDto(createdProvince);
        } catch (OptimisticEntityLockException e) {
            throw new UpdateResourceException("Can not create new province");
        }
    }

    @Override
    public List<ProvinceDto> getAll(Boolean isDisable, Integer pageNumber, Integer pageSize, String sortBy, String sortType) {
        try {
            if(isDisable != null && isDisable){
                return getAllAvailable(pageNumber, pageSize, sortBy, sortType);
            }
            List<Province> provinces = ServiceUtil.getAll(this::count, isDisable, pageNumber, pageSize, sortBy, sortType, provinceRepository::findAllByDisable, provinceRepository::findAllByDisable);

            return provinces.stream().map(provinceMapper::toDto).toList();
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("Get provinces failed");
        }
    }

    @Override
    public List<ProvinceDto> getAllAvailable(Integer pageNumber, Integer pageSize, String sortBy, String sortType) {
        try {
            List<Province> provinces = ServiceUtil.getAllAvailable(this::count, pageNumber, pageSize, sortBy, sortType, provinceRepository::findAll, provinceRepository::findAll);
            return provinceMapper.toListDto(provinces);
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("Get provinces failed");
        }
    }

    @Override
    public ProvinceDto getById(UUID id) {
        try {
            Province province = provinceRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            ProvinceDto provinceDto = provinceMapper.toDto(province);
            provinceDto.setDistricts(districtMapper.toListDto(province.getDistricts()));
            return provinceDto;
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("Province with id = " + id + " not found");
        }
    }

    @Override
    public ProvinceDto update(UUID id, ProvinceDto provinceDto) {
        try {
            Province province = provinceRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            provinceMapper.transferToEntity(province, provinceDto);
            Province updatedProvince = provinceRepository.save(province);
//            provinceRepository.refresh(updatedProvince);
            return provinceMapper.toDto(updatedProvince);
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            throw new ResourceNotFoundException("Province with id = " + id + " not found");
        } catch (OptimisticEntityLockException e) {
            throw new UpdateResourceException("Can not update province");
        }
    }

    @Override
    public void updateDisable(UUID id) {
        try {
            Province province = provinceRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            province.setDisable(!province.isDisable());
            provinceRepository.save(province);
        } catch (IllegalArgumentException | EntityNotFoundException ex) {
            throw new ResourceNotFoundException("Province with id = " + id + " not found");
        } catch (OptimisticEntityLockException e) {
            throw new UpdateResourceException("Can not update province");
        }
    }

    @Override
    public Long count(Boolean isDisable) {
        if(isDisable == null){
            return provinceRepository.count();
        }
        return provinceRepository.countByDisable(isDisable);
    }
}
