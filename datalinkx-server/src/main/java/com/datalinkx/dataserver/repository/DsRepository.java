package com.datalinkx.dataserver.repository;

import java.util.List;
import java.util.Optional;

import com.blazebit.persistence.PagedList;
import com.datalinkx.dataserver.bean.domain.DsBean;
import com.datalinkx.dataserver.bean.domain.PageDomain;
import org.springframework.data.repository.NoRepositoryBean;



@NoRepositoryBean
public interface DsRepository extends BaseRepository<DsBean, String> {

	Optional<DsBean> findByDsId(String dsId);

	List<DsBean> findAllByIsDel(Integer isDel);

	DsBean findByName(String name);

	List<DsBean> findAllByDsIdIn(List<String> dsIds);

	PagedList<DsBean> pageQuery(PageDomain pageDomain, String name, Integer type);

	void deleteByDsId(String dsId);
}
