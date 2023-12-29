package com.sem4project.sem4.service;

import com.sem4project.sem4.dto.dtomodel.RoleDto;
import com.sem4project.sem4.entity.Role;

public interface RoleService extends BaseService<Role, RoleDto>{
    void init();
    Long count(Boolean isDisable);
}
