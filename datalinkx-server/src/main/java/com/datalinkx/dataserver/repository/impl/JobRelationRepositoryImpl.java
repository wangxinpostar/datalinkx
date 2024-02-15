package com.datalinkx.dataserver.repository.impl;

import java.util.List;

import com.blazebit.persistence.PagedList;
import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.blazebit.persistence.querydsl.BlazeJPAQueryFactory;
import com.datalinkx.dataserver.bean.domain.JobRelationBean;
import com.datalinkx.dataserver.bean.domain.PageDomain;
import com.datalinkx.dataserver.bean.domain.QJobRelationBean;
import com.datalinkx.dataserver.repository.JobRelationRepository;
import com.datalinkx.dataserver.repository.SelectBooleanBuilder;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class JobRelationRepositoryImpl extends BaseRepositoryImpl<JobRelationBean, String> implements JobRelationRepository {

    public JobRelationRepositoryImpl(EntityManager em, BlazeJPAQueryFactory jpaQueryFactory) {
        super(JobRelationBean.class, em, jpaQueryFactory);
    }

    final QJobRelationBean qJobRelationBean = QJobRelationBean.jobRelationBean;
    @Override
    public PagedList<JobRelationBean> pageQuery(PageDomain pageDomain, String jobId) {
        BlazeJPAQuery<JobRelationBean> pageQueryRes = blazeJPAQueryFactory.selectFrom(qJobRelationBean).where(
                SelectBooleanBuilder.builder()
                        .notEmptyEq(jobId, qJobRelationBean.jobId)
                        .notEmptyEq(0, qJobRelationBean.isDel)
                        .build()
        );
        return this.fetchPage(pageQueryRes, pageDomain);
    }

    @Override
    public List<JobRelationBean> list() {
        return blazeJPAQueryFactory.selectFrom(qJobRelationBean).where(qJobRelationBean.isDel.eq(0)).fetch();
    }

    @Override
    public void logicDeleteByRelationId(String relationId) {
        blazeJPAQueryFactory.update(qJobRelationBean)
                .set(qJobRelationBean.isDel, 1)
                .where(qJobRelationBean.relationId.eq(relationId))
                .execute();
    }

    // 查找当前任务的父任务
    @Override
    public List<JobRelationBean> findSubJob(String jobId) {
        return blazeJPAQueryFactory.selectFrom(qJobRelationBean)
                .where(qJobRelationBean.jobId.eq(jobId)
                        .and(qJobRelationBean.isDel.eq(0))
                ).fetch();
    }

    @Override
    public List<JobRelationBean> findParentJob(String jobId) {
        return blazeJPAQueryFactory.selectFrom(qJobRelationBean)
                .where(qJobRelationBean.subJobId.eq(jobId)
                        .and(qJobRelationBean.isDel.eq(0))
                ).fetch();
    }
}
