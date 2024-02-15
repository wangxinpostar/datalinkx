package com.datalinkx.dataserver.repository;

import java.util.List;

import com.blazebit.persistence.PagedList;
import com.datalinkx.dataserver.bean.domain.JobRelationBean;
import com.datalinkx.dataserver.bean.domain.PageDomain;

public interface JobRelationRepository extends CRUDRepository<JobRelationBean, String> {
    PagedList<JobRelationBean> pageQuery(PageDomain pageDomain, String jobId);

    List<JobRelationBean> list();

    void logicDeleteByRelationId(String relationId);

    List<JobRelationBean> findSubJob(String jobId);

    List<JobRelationBean> findParentJob(String jobId);
}
