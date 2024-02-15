package com.datalinkx.dataserver.repository;

import com.blazebit.persistence.PagedList;
import com.datalinkx.dataserver.bean.domain.JobLogBean;
import com.datalinkx.dataserver.bean.domain.PageDomain;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface JobLogRepository extends CRUDRepository<JobLogBean, String> {


    PagedList<JobLogBean> pageQuery(PageDomain pageDomain, String jobId);

    void logicDeleteByJobId(String jobId);
}
