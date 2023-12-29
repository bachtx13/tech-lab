package com.sem4project.sem4.repository;

import com.sem4project.sem4.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BranchRepository extends BaseRepository<Branch, UUID> {
    Long countByDisable(Boolean isDisable);
}
