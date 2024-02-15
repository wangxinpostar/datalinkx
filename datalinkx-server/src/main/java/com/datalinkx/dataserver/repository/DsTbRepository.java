package com.datalinkx.dataserver.repository;

import java.util.List;
import java.util.Optional;

import com.datalinkx.dataserver.bean.domain.DsTbBean;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface DsTbRepository extends CRUDRepository<DsTbBean, String> {

	Optional<DsTbBean> findByTbId(String tbId);

	DsTbBean findTopByNameAndDsId(String name, String dsId);

	List<DsTbBean> findAllByTbIdIn(List<String> tbIds);
}
