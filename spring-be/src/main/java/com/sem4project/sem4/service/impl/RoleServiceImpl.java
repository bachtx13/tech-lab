package com.sem4project.sem4.service.impl;

import com.sem4project.sem4.common.RoleEnum;
import com.sem4project.sem4.dto.dtomodel.RoleDto;
import com.sem4project.sem4.entity.Role;
import com.sem4project.sem4.repository.RoleRepository;
import com.sem4project.sem4.service.RoleService;
import com.sem4project.sem4.service.utils.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void init() {
        Arrays.stream(RoleEnum.values()).forEach(r -> {
            roleRepository.save(Role.builder()
                    .name(r.name())
                    .build());
        });
    }

    @Override
    public Long count(Boolean isDisable) {
        if(isDisable == null){
            return roleRepository.count();
        }
        return roleRepository.countByDisable(isDisable);
    }

    @Override
    public RoleDto getById(UUID id) {
        return null;
    }

    @Override
    public List<RoleDto> getAll(Boolean isDisable, Integer pageNumber, Integer pageSize, String sortBy, String sortType) {
        return null;
    }

    @Override
    public List<RoleDto> getAllAvailable(Integer pageNumber, Integer pageSize, String sortBy, String sortType) {
        return null;
    }

    @Override
    public RoleDto create(RoleDto brandDto) {
        return null;
    }

    @Override
    public RoleDto update(UUID id, RoleDto brandDto) {
        return null;
    }

    @Override
    public void updateDisable(UUID id) {

    }
}
