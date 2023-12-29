
package com.sem4project.sem4.repository;

import com.sem4project.sem4.entity.Photo;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PhotoRepository extends BaseRepository<Photo, UUID> {
    Long countByDisable(Boolean isDisable);
}
