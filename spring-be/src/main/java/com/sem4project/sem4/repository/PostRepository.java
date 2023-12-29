package com.sem4project.sem4.repository;

import com.sem4project.sem4.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostRepository extends BaseRepository<Post, UUID> {
    Long countByDisable(Boolean isDisable);
}
