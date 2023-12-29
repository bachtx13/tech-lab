package com.sem4project.sem4.service.impl;

import com.sem4project.sem4.dto.dtomodel.DistrictDto;
import com.sem4project.sem4.entity.District;
import com.sem4project.sem4.entity.Province;
import com.sem4project.sem4.exception.UpdateResourceException;
import com.sem4project.sem4.exception.ResourceNotFoundException;
import com.sem4project.sem4.mapper.DistrictMapper;
import com.sem4project.sem4.repository.DistrictRepository;
import com.sem4project.sem4.repository.ProvinceRepository;
import com.sem4project.sem4.service.BaseService;
import com.sem4project.sem4.service.DistrictService;
import com.sem4project.sem4.service.utils.ServiceUtil;
import com.sem4project.sem4.util.PageableUtil;
import lombok.AllArgsConstructor;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DistrictServiceImpl implements DistrictService {
    private final DistrictRepository districtRepository;
    private final ProvinceRepository provinceRepository;
    private final DistrictMapper districtMapper = DistrictMapper.INSTANCE;

    @Override
    public DistrictDto create(DistrictDto districtDto) {
        try {
            Province province = provinceRepository.findById(districtDto.getProvince().getId()).orElseThrow(IllegalArgumentException::new);
            District district = districtMapper.toEntity(districtDto);
            district.setProvince(province);
            District createdDistrict = districtRepository.save(district);
//            districtRepository.refresh(createdDistrict);
            return districtMapper.toDto(createdDistrict);
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("District with id = " + districtDto.getId() + " not found");
        } catch (OptimisticEntityLockException ex){
            throw new UpdateResourceException("Create district failed");
        }
    }

    @Override
    public List<DistrictDto> getAll(Boolean isDisable, Integer pageNumber, Integer pageSize, String sortBy, String sortType) {
        try {
            if(isDisable != null && isDisable){
                return getAllAvailable(pageNumber, pageSize, sortBy, sortType);
            }
            List<District> districts = ServiceUtil.getAll(this::count, isDisable, pageNumber, pageSize, sortBy, sortType, districtRepository::findAllByDisable, districtRepository::findAllByDisable);

            return districts.stream().map(districtMapper::toDto).toList();
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("Get districts failed");
        }
    }

    @Override
    public List<DistrictDto> getAllAvailable(Integer pageNumber, Integer pageSize, String sortBy, String sortType) {
        try {
            List<District> districts = ServiceUtil.getAllAvailable(this::count, pageNumber, pageSize, sortBy, sortType, districtRepository::findAll, districtRepository::findAll);
            return districtMapper.toListDto(districts);
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("Get districts failed");
        }
    }

    @Override
    public DistrictDto getById(UUID id) {
        try {
            District district = districtRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            return districtMapper.toDto(district);
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("District with id = " + id + " not found");
        }
    }

    @Override
    public DistrictDto update(UUID id, DistrictDto districtDto) {
        try {
            District district = districtRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            districtMapper.transferToEntity(district, districtDto);
            District updatedDistrict = districtRepository.save(district);
//            districtRepository.refresh(updatedDistrict);
            return districtMapper.toDto(updatedDistrict);
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("District with id = " + id + " not found");
        } catch (OptimisticEntityLockException ex){
            throw new UpdateResourceException("Update district failed");
        }
    }

    @Override
    public void updateDisable(UUID id) {
        try {
            District district = districtRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            district.setDisable(!district.isDisable());
            districtRepository.save(district);
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("District with id = " + id + " not found");
        } catch (OptimisticEntityLockException ex){
            throw new UpdateResourceException("Create district failed");
        }
    }

    @Override
    public Long count(Boolean isDisable) {
        if(isDisable == null){
            return districtRepository.count();
        }
        return districtRepository.countByDisable(isDisable);
    }
}
