package com.sem4project.sem4.repository;

import com.sem4project.sem4.entity.Role;
import com.sem4project.sem4.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends BaseRepository<User, UUID> {
    @EntityGraph(attributePaths = {"roles"})
    Optional<User> findByEmail(String email);
    @EntityGraph(attributePaths = {"roles", "userInfo"})
    List<User> findAllByDisable(boolean isDisable);
    @EntityGraph(attributePaths = {"roles", "userInfo"})
    List<User> findAll();
    Boolean existsByEmail(String email);
    Long countByDisable(Boolean isDisable);
    @EntityGraph(attributePaths = {"roles", "userInfo"})
    Optional<User> findByOauth2Email(String oauth2Email);
    @Query("SELECT COUNT(u) FROM User u JOIN u.roles r WHERE r.name = :roleName")
    long countUsersByRoleName(@Param("roleName") String roleName);
}
