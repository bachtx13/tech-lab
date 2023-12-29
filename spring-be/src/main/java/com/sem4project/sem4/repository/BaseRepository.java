package com.sem4project.sem4.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
    void refresh(T t);
    Long countByDisable(Boolean isDisable);
    List<T> findAllByDisable(Boolean isDisable, Sort sort);
    List<T> findAllByDisable(Boolean isDisable, Pageable pageable);
    void persist(T t);
}
