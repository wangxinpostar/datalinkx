package com.datalinkx.dataserver.config;

import com.blazebit.persistence.Criteria;
import com.blazebit.persistence.querydsl.BlazeJPAQueryFactory;
import com.blazebit.persistence.spi.CriteriaBuilderConfiguration;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration(proxyBeanMethods = false)
public class BlazePersistenceConfig {

    @Autowired
    private EntityManagerFactory entityManagerFactory;


    @Bean
    public BlazeJPAQueryFactory createBlazeJPAQuery(EntityManager entityManager, EntityManagerFactory entityManagerFactory) {
        CriteriaBuilderConfiguration config = Criteria.getDefault();
        return new BlazeJPAQueryFactory(entityManager, config.createCriteriaBuilderFactory(entityManagerFactory));
    }
}
