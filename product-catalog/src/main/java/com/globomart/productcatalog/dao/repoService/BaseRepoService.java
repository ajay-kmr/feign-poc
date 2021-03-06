package com.globomart.productcatalog.dao.repoService;

import com.globomart.productcatalog.dao.entity.BaseEntity;
import com.globomart.productcatalog.dto.DataTableRequestDTO;
import com.globomart.productcatalog.service.impl.BaseServiceImpl;
import lombok.extern.apachecommons.CommonsLog;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.in;

@CommonsLog
public abstract class BaseRepoService<T extends BaseEntity, ID extends Serializable> extends BaseServiceImpl {

    protected abstract JpaRepository<T, ID> getRepository();

    abstract protected Class<T> getEntityClass();

    @PersistenceContext
    protected EntityManager entityManager;

    protected Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    protected Criteria getCriteria() {
        return getSession().createCriteria(getEntityClass())
                .add(eq("deleted", false));
    }

    protected Criteria addPagingAndSorting(Criteria criteria, DataTableRequestDTO dataTableSearchDTO) {
        addPagingAndSorting(criteria, dataTableSearchDTO.getMax(), dataTableSearchDTO.getOffset(),
                dataTableSearchDTO.getOrderCriteria());
        return criteria;
    }

    private void addPagingAndSorting(Criteria criteria, int max, int offset, Order order) {
        criteria.setFirstResult(offset)
                .setMaxResults(max)
                .addOrder(order);
    }

    public long count() {
        return getRepository().count();
    }

    public <S extends T> long count(Example<S> example) {
        return getRepository().count(example);
    }

    public Long count(Criteria criteria) {
        criteria.setFirstResult(0);
        criteria.setProjection(Projections.rowCount());
        return (Long) criteria.uniqueResult();
    }

    public <S extends T> boolean exists(Example<S> example) {
        return getRepository().exists(example);
    }

    public T getOne(ID id) {
        return getRepository().getOne(id);
    }

    public T findOne(ID id) {
        if (id == null) {
            return null;
        }
        return getRepository().findOne(id);
    }

    public <S extends T> S findOne(Example<S> example) {
        return getRepository().findOne(example);
    }

    public List<T> findAll() {
        return getRepository().findAll();
    }

    public List<T> findAll(Sort sort) {
        return getRepository().findAll(sort);
    }

    public Page<T> findAll(Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    public List<T> findAll(Iterable<ID> ids) {
        return getRepository().findAll(ids);
    }

    public <S extends T> List<S> findAll(Example<S> example) {
        return getRepository().findAll(example);
    }

    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return getRepository().findAll(example, sort);
    }

    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        return getRepository().findAll(example, pageable);
    }

    public void flush() {
        getRepository().flush();
    }

    public <S extends T> S save(S entity) {
        return getRepository().save(entity);
    }

    public <S extends T> S saveAndFlush(S entity) {
        return getRepository().saveAndFlush(entity);
    }

    public <S extends T> List<S> save(Iterable<S> entities) {
        if (entities != null) {
            return getRepository().save(entities);
        }
        return null;
    }

    public void delete(ID id) {
        if (id != null) {
            T entity = getRepository().findOne(id);
            if (entity != null) {
                delete(entity);
            }
        }
        //TODO Discuss whether this should be hard delete or soft delete.
//        getRepository().delete(id);

    }

    public void delete(T entity) {
        if (entity != null) {
            //TODO Discuss whether this should be hard delete or soft delete.
//        getRepository().delete(entity);
            entity.setDeleted(true);
            save(entity);
        }
    }

    public void delete(Iterable<? extends T> entities) {
        if (entities != null) {
            //TODO Discuss whether this should be har delete or soft delete.
//        getRepository().delete(entities);
            entities.forEach(it -> it.setDeleted(true));
            save(entities);
        }
    }

    public void deleteAll() {
        //TODO Discuss whether this should be har delete or soft delete.
//        getRepository().deleteAll();
        throw new IllegalArgumentException("Delete All is restricted..");
    }

    public void deleteInBatch(Iterable<T> entities) {
        //TODO Discuss whether this should be har delete or soft delete.
//        getRepository().deleteInBatch(entities);
        throw new IllegalArgumentException("DeleteInBatch is restricted..");
    }

    public void deleteAllInBatch() {
        //TODO Discuss whether this should be har delete or soft delete.
        getRepository().deleteAllInBatch();
    }

    public List<ID> findValidIds(List<ID> idsForValidation) {
        idsForValidation = idsForValidation.stream().filter(Objects::nonNull).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(idsForValidation)) {
            return Collections.emptyList();
        }
        List<T> result = getRepository().findAll(idsForValidation);
        if (CollectionUtils.isEmpty(result)) {
            return Collections.emptyList();
        }
        return result.stream().map((T it) -> (ID) it.getId()).collect(Collectors.toList());
    }
}
