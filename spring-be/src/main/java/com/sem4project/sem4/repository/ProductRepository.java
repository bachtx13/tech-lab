package com.sem4project.sem4.repository;

import com.sem4project.sem4.entity.Category;
import com.sem4project.sem4.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends BaseRepository<Product, UUID> {
    Long countByDisable(Boolean isDisable);
    List<Product> findAllByCostBetweenAndDisableFalse(Double min, Double max);

    List<Product> findAllByDisableFalse();

}
