package com.sem4project.sem4.repository;

import com.sem4project.sem4.entity.District;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DistrictRepository extends BaseRepository<District, UUID> {
    List<District> findAllByDisable(boolean isDisable, Pageable pageable);
    List<District> findAllByDisable(boolean isDisable, Sort sort);
    Long countByDisable(Boolean isDisable);
}
