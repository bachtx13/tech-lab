package com.sem4project.sem4.repository;

import com.sem4project.sem4.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends BaseRepository<Role, UUID> {
    Optional<Role> findByName(String name);
    Long countByDisable(Boolean isDisable);
}
