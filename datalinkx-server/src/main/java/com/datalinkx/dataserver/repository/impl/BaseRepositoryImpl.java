package com.datalinkx.dataserver.repository.impl;

import java.util.Optional;

import com.blazebit.persistence.PagedList;
import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.blazebit.persistence.querydsl.BlazeJPAQueryFactory;
import com.datalinkx.dataserver.bean.domain.PageDomain;
import com.datalinkx.dataserver.repository.BaseRepository;
import com.querydsl.core.types.OrderSpecifier;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;


public abstract class BaseRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    private final EntityManager em;
    protected final BlazeJPAQueryFactory blazeJPAQueryFactory;

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em, BlazeJPAQueryFactory jpaQueryFactory) {
        super(domainClass, em);
        this.em = em;
        this.blazeJPAQueryFactory = jpaQueryFactory;
    }

    @Override
    public void clear() {
        em.clear();
    }

    @Override
    public void detach(T entity) {
        em.detach(entity);
    }

    public Query nativeQuery(String sql, Class clazz){
        return em.createNativeQuery(sql, clazz);
    }

    public Query nativeQuery(String sql){
        return em.createNativeQuery(sql);
    }

    @Override
    public <K> PagedList<K>  fetchPage(BlazeJPAQuery<K> jpaQuery, PageDomain pageDomain){
        Optional<OrderSpecifier> dslOrderBy = pageDomain.getDslOrderBy();
        dslOrderBy.map(jpaQuery::orderBy);
        return jpaQuery.fetchPage(pageDomain.offset(), pageDomain.getPageSize());
    }
}
