package com.sem4project.sem4.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID>{
    private final EntityManager entityManager;
    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em, EntityManager entityManager) {
        super(domainClass, em);
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void refresh(T t) {
        entityManager.refresh(t);
    }

    @Override
    public Long countByDisable(Boolean isDisable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<T> root = cq.from(getDomainClass());
        cq.select(cb.count(root))
                .where(cb.equal(root.get("disable"), isDisable));
        return entityManager.createQuery(cq).getSingleResult();
    }

    @Override
    public List<T> findAllByDisable(Boolean isDisable, Sort sort) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(getDomainClass());
        Root<T> root = cq.from(getDomainClass());
        cq.where(cb.equal(root.get("disable"), isDisable));
        cq.orderBy(QueryUtils.toOrders(sort, root, cb));
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<T> findAllByDisable(Boolean isDisable, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(getDomainClass());
        Root<T> root = cq.from(getDomainClass());
        cq.where(cb.equal(root.get("disable"), isDisable));
        TypedQuery<T> query = entityManager.createQuery(cq);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        Long count = countByDisable(isDisable);
        return new PageImpl<>(query.getResultList(), pageable, count).stream().toList();
    }

    @Override
    @Transactional
    public void persist(T t) {
        this.entityManager.persist(t);
    }
}
