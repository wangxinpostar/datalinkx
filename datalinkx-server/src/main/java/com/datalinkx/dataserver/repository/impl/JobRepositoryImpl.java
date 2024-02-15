package com.datalinkx.dataserver.repository.impl;

import java.util.List;
import java.util.Optional;

import com.blazebit.persistence.PagedList;
import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.blazebit.persistence.querydsl.BlazeJPAQueryFactory;
import com.datalinkx.dataserver.bean.domain.JobBean;
import com.datalinkx.dataserver.bean.domain.PageDomain;
import com.datalinkx.dataserver.bean.domain.QJobBean;
import com.datalinkx.dataserver.repository.JobRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class JobRepositoryImpl extends BaseRepositoryImpl<JobBean, String> implements JobRepository {
    QJobBean qJobBean = QJobBean.jobBean;

    public JobRepositoryImpl(EntityManager em, BlazeJPAQueryFactory jpaQueryFactory) {
        super(JobBean.class, em, jpaQueryFactory);
    }

    @Override
    public Optional<JobBean> findByJobId(String jobId) {
        return Optional.ofNullable(
                blazeJPAQueryFactory.selectFrom(qJobBean)
                .where(qJobBean.jobId.eq(jobId).and(qJobBean.isDel.eq(0)))
                .fetchOne()
        );
    }

    @Override
    public List<JobBean> findByJobIdIn(List<String> jobIds) {
        return blazeJPAQueryFactory.selectFrom(qJobBean)
                .where(qJobBean.jobId.in(jobIds).and(qJobBean.isDel.eq(0)))
                .fetch();
    }

    @Override
    public List<JobBean> findAll() {
        return blazeJPAQueryFactory.selectFrom(qJobBean)
                .where(qJobBean.isDel.eq(0))
                .fetch();
    }

    @Override
    public void updateJobStatus(String jobId, Integer status) {
        blazeJPAQueryFactory.update(qJobBean).set(qJobBean.status, status).where(qJobBean.jobId.eq(jobId).and(qJobBean.isDel.eq(0))).execute();
    }

    @Override
    public void logicDeleteByJobId(String jobId) {
        blazeJPAQueryFactory.update(qJobBean).set(qJobBean.isDel, 1).where(qJobBean.jobId.eq(jobId)).execute();
    }

    @Override
    public PagedList<JobBean> pageQuery(PageDomain pageDomain) {
        BlazeJPAQuery<JobBean> jpaQuery = blazeJPAQueryFactory.selectFrom(qJobBean).where(qJobBean.isDel.eq(0)).orderBy(qJobBean.id.asc());
        return this.fetchPage(jpaQuery, pageDomain);
    }
}
