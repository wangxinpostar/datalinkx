package com.datalinkx.dataserver.repository.impl;

import com.blazebit.persistence.PagedList;
import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.blazebit.persistence.querydsl.BlazeJPAQueryFactory;
import com.datalinkx.dataserver.bean.domain.JobLogBean;
import com.datalinkx.dataserver.bean.domain.PageDomain;
import com.datalinkx.dataserver.bean.domain.QJobLogBean;
import com.datalinkx.dataserver.repository.JobLogRepository;
import com.datalinkx.dataserver.repository.SelectBooleanBuilder;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class JobLogRepositoryImpl extends BaseRepositoryImpl<JobLogBean, String> implements JobLogRepository {

    final QJobLogBean qJobLogBean = QJobLogBean.jobLogBean;
    public JobLogRepositoryImpl(EntityManager em, BlazeJPAQueryFactory jpaQueryFactory) {
        super(JobLogBean.class, em, jpaQueryFactory);
    }

    @Override
    public PagedList<JobLogBean> pageQuery(PageDomain pageDomain, String jobId) {
        BlazeJPAQuery<JobLogBean> jpaQuery = blazeJPAQueryFactory.selectFrom(qJobLogBean)
                .where(
                        SelectBooleanBuilder.builder()
                                .notEmptyEq(0, qJobLogBean.isDel)
                                .notEmptyLike(jobId, qJobLogBean.jobId)
                                .build()
                ).orderBy(qJobLogBean.id.asc());

        return this.fetchPage(jpaQuery, pageDomain);
    }

    @Override
    public void logicDeleteByJobId(String jobId) {
        blazeJPAQueryFactory.update(qJobLogBean).set(qJobLogBean.isDel, 1).where(qJobLogBean.jobId.eq(jobId)).execute();
    }
}
