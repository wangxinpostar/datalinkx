package com.datalinkx.dataserver.repository;

import java.util.List;
import java.util.Optional;

import com.blazebit.persistence.PagedList;
import com.datalinkx.dataserver.bean.domain.JobBean;
import com.datalinkx.dataserver.bean.domain.PageDomain;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;


@NoRepositoryBean
public interface JobRepository extends CRUDRepository<JobBean, String> {

	Optional<JobBean> findByJobId(String jobId);

	Optional<JobBean> findByName(String name);

	List<JobBean> findByJobIdIn(List<String> jobIds);

	@Query(value = "select * from JOB where (reader_ds_id = :jobId or writer_ds_id = :jobId)", nativeQuery = true)
	List<JobBean> findDependJobId(String jobId);

	@Query(value = "select * from JOB where is_del = 0", nativeQuery = true)
	List<JobBean> findAll();

	@Modifying
	@Transactional
	void updateJobStatus(String jobId, Integer status);

	@Modifying
	@Transactional
	void logicDeleteByJobId(String jobId);


	PagedList<JobBean> pageQuery(PageDomain pageDomain);
}
