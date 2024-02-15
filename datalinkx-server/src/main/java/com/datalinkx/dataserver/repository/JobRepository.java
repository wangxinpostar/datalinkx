package com.datalinkx.dataserver.repository;

import java.util.List;
import java.util.Optional;

import com.blazebit.persistence.PagedList;
import com.datalinkx.dataserver.bean.domain.JobBean;
import com.datalinkx.dataserver.bean.domain.PageDomain;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;


@NoRepositoryBean
public interface JobRepository extends CRUDRepository<JobBean, String> {

	Optional<JobBean> findByJobId(String jobId);

	List<JobBean> findByJobIdIn(List<String> jobIds);

	List<JobBean> findAll();

	@Modifying
	@Transactional
	void updateJobStatus(String jobId, Integer status);

	@Modifying
	@Transactional
	void logicDeleteByJobId(String jobId);


	PagedList<JobBean> pageQuery(PageDomain pageDomain);
}
