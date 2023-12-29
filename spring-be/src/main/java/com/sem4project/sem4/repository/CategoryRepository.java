package com.sem4project.sem4.repository;

import com.sem4project.sem4.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNullApi;
import org.springframework.lang.NonNullFields;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends BaseRepository<Category, UUID> {
    @EntityGraph(attributePaths = {"categories"})
    Optional<Category> findById(UUID id);
    Long countByDisable(Boolean isDisable);
    Boolean existsAllByIdIn(List<UUID> idList);
    List<Category> findAllByParentCategoryNull(Sort sort);
    Page<Category> findAllByParentCategoryNull(Pageable pageable);
    List<Category> findAllByDisableAndParentCategoryNull(boolean isDisable, Sort sort);
    List<Category> findAllByDisableAndParentCategoryNull(boolean isDisable, Pageable pageable);
}
