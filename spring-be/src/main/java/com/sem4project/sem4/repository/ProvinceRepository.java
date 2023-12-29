
package com.sem4project.sem4.repository;

import com.sem4project.sem4.entity.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProvinceRepository extends BaseRepository<Province, UUID> {
    Page<Province> findAllByDisable(boolean isDisable, Pageable pageable);
    Long countByDisable(Boolean isDisable);
}
