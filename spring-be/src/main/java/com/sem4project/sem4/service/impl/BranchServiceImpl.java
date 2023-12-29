package com.sem4project.sem4.service.impl;

import com.sem4project.sem4.dto.dtomodel.BranchDto;
import com.sem4project.sem4.dto.dtomodel.BrandDto;
import com.sem4project.sem4.entity.Branch;
import com.sem4project.sem4.entity.Brand;
import com.sem4project.sem4.entity.District;
import com.sem4project.sem4.exception.ResourceNotFoundException;
import com.sem4project.sem4.exception.UpdateResourceException;
import com.sem4project.sem4.mapper.BranchMapper;
import com.sem4project.sem4.mapper.BrandMapper;
import com.sem4project.sem4.repository.BranchRepository;
import com.sem4project.sem4.service.BaseService;
import com.sem4project.sem4.service.BrandService;
import com.sem4project.sem4.service.utils.ServiceUtil;
import lombok.AllArgsConstructor;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class BranchServiceImpl implements BaseService<Branch, BranchDto> {
    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper = BranchMapper.INSTANCE;

    @Override
    public BranchDto getById(UUID id) {
        try {
            Branch brand = branchRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            return branchMapper.toDto(brand);
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("Branch with id = " + id + " not found");
        }
    }

    @Override
    public List<BranchDto> getAll(Boolean isDisable, Integer pageNumber, Integer pageSize, String sortBy, String sortType) {
        try {
            if (isDisable != null && isDisable) {
                return getAllAvailable(pageNumber, pageSize, sortBy, sortType);
            }
            List<Branch> branches = ServiceUtil.getAll(this::count, isDisable, pageNumber, pageSize, sortBy, sortType, branchRepository::findAllByDisable, branchRepository::findAllByDisable);

            return branches.stream().map(branchMapper::toDto).toList();
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("Get branches failed");
        }
    }

    @Override
    public List<BranchDto> getAllAvailable(Integer pageNumber, Integer pageSize, String sortBy, String sortType) {
        try {
            List<Branch> branches = ServiceUtil.getAllAvailable(this::count,
                    pageNumber, pageSize, sortBy, sortType,
                    branchRepository::findAll, branchRepository::findAll);
            return branchMapper.toListDto(branches);
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("Get branches failed");
        }
    }

    @Override
    public BranchDto create(BranchDto branchDto) {
        try {
            Branch branch = branchMapper.toEntity(branchDto);
            Branch createdBranch = branchRepository.save(branch);
//            branchRepository.refresh(createdBranch);
            return branchMapper.toDto(createdBranch);
        } catch (OptimisticEntityLockException e) {
            throw new UpdateResourceException("Can not create new branch");
        }
    }

    @Override
    public BranchDto update(UUID id, BranchDto branchDto) {
        try {
            Branch branch = branchRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            branchMapper.transferToEntity(branch, branchDto);
            Branch updatedBranch = branchRepository.save(branch);
//            branchRepository.refresh(updatedBranch);
            return branchMapper.toDto(updatedBranch);
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("District with id = " + id + " not found");
        } catch (OptimisticEntityLockException ex) {
            throw new UpdateResourceException("Update branch failed");
        }
    }

    @Override
    public void updateDisable(UUID id) {
        try {
            Branch branch = branchRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            branch.setDisable(!branch.isDisable());
            branchRepository.save(branch);
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("Branch with id = " + id + " not found");
        } catch (OptimisticEntityLockException ex) {
            throw new UpdateResourceException("Can not update branch");
        }
    }

    @Override
    public Long count(Boolean isDisable) {
        if (isDisable == null) {
            return branchRepository.count();
        }
        return branchRepository.countByDisable(isDisable);
    }
}
