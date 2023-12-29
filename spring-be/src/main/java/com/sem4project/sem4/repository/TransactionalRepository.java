package com.sem4project.sem4.repository;

import com.sem4project.sem4.entity.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionalRepository extends BaseRepository<Transactional, UUID> {
    Long countByDisable(Boolean isDisable);
}
