package com.datalinkx.dataserver.repository.impl;

import java.util.List;
import java.util.Optional;

import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.blazebit.persistence.querydsl.BlazeJPAQueryFactory;
import com.datalinkx.dataserver.bean.domain.DsTbBean;
import com.datalinkx.dataserver.bean.domain.QDsTbBean;
import com.datalinkx.dataserver.repository.DsTbRepository;
import com.datalinkx.dataserver.repository.SelectBooleanBuilder;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class DsTbRepositoryImpl extends BaseRepositoryImpl<DsTbBean, String> implements DsTbRepository {

	final QDsTbBean qDsTbBean = QDsTbBean.dsTbBean;
	public DsTbRepositoryImpl(EntityManager em, BlazeJPAQueryFactory jpaQueryFactory) {
		super(DsTbBean.class, em, jpaQueryFactory);
	}

	@Override
	public Optional<DsTbBean> findByTbId(String tbId) {
		BlazeJPAQuery<DsTbBean> dsTbBeanBlazeJPAQuery = blazeJPAQueryFactory.selectFrom(qDsTbBean)
				.where(
						SelectBooleanBuilder.builder()
								.notEmptyEq(tbId, qDsTbBean.tbId)
								.notEmptyEq(0, qDsTbBean.isDel)
								.build()
				);
		return Optional.ofNullable(dsTbBeanBlazeJPAQuery.fetchOne());
	}

	@Override
	public DsTbBean findTopByNameAndDsId(String name, String dsId) {
		BlazeJPAQuery<DsTbBean> dsTbBeanBlazeJPAQuery = blazeJPAQueryFactory.selectFrom(qDsTbBean)
				.where(
						SelectBooleanBuilder.builder()
								.notEmptyEq(name, qDsTbBean.name)
								.notEmptyEq(dsId, qDsTbBean.dsId)
								.notEmptyEq(0, qDsTbBean.isDel)
								.build()
				);
		return dsTbBeanBlazeJPAQuery.fetchOne();
	}

	@Override
	public List<DsTbBean> findAllByTbIdIn(List<String> tbIds) {
		return blazeJPAQueryFactory.selectFrom(qDsTbBean).where(qDsTbBean.tbId.in(tbIds).and(qDsTbBean.isDel.eq(0))).fetch();
	}
}
