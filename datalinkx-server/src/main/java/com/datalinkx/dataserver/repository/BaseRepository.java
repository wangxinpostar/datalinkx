package com.datalinkx.dataserver.repository;

import com.blazebit.persistence.PagedList;
import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.datalinkx.dataserver.bean.domain.PageDomain;
import jakarta.persistence.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T,ID> extends JpaRepository<T,ID> {
    void clear();

    void detach(T entity);

    <K> PagedList<K> fetchPage(BlazeJPAQuery<K> jpaQuery, PageDomain pageDomain);

    Query nativeQuery(String sql, Class clazz);
}
