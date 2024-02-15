package com.datalinkx.dataserver.repository.impl;

import java.util.List;
import java.util.Optional;

import com.blazebit.persistence.PagedList;
import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.blazebit.persistence.querydsl.BlazeJPAQueryFactory;
import com.datalinkx.dataserver.bean.domain.DsBean;
import com.datalinkx.dataserver.bean.domain.PageDomain;
import com.datalinkx.dataserver.bean.domain.QDsBean;
import com.datalinkx.dataserver.repository.DsRepository;
import com.datalinkx.dataserver.repository.SelectBooleanBuilder;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class DsRepositoryImpl extends BaseRepositoryImpl<DsBean, String> implements DsRepository {

	public DsRepositoryImpl(EntityManager em, BlazeJPAQueryFactory factory) {
		super(DsBean.class, em, factory);
	}
	final QDsBean qDsBean = QDsBean.dsBean;


	@Override
	public Optional<DsBean> findByDsId(String dsId) {
		BlazeJPAQuery<DsBean> dsBeanBlazeJPAQuery = blazeJPAQueryFactory.selectFrom(qDsBean)
				.where(
						SelectBooleanBuilder.builder()
								.notEmptyEq(dsId, qDsBean.dsId)
								.notEmptyEq(0, qDsBean.isDel)
								.build()
				);
		return Optional.ofNullable(dsBeanBlazeJPAQuery.fetchOne());
	}

	@Override
	public List<DsBean> findAllByIsDel(Integer isDel) {
		return blazeJPAQueryFactory.selectFrom(qDsBean).where(qDsBean.isDel.eq(0)).fetch();
	}

	@Override
	public DsBean findByName(String name) {
		return blazeJPAQueryFactory.selectFrom(qDsBean).where(qDsBean.name.eq(name).and(qDsBean.isDel.eq(0))).fetchOne();
	}

	@Override
	public List<DsBean> findAllByDsIdIn(List<String> dsIds) {
		return blazeJPAQueryFactory.selectFrom(qDsBean).where(qDsBean.dsId.in(dsIds).and(qDsBean.isDel.eq(0))).fetch();
	}

	@Override
	public PagedList<DsBean> pageQuery(PageDomain pageDomain, String name, Integer type) {
		BlazeJPAQuery<DsBean> jpaQuery = blazeJPAQueryFactory.selectFrom(qDsBean)
				.where(
						SelectBooleanBuilder.builder()
								.notEmptyEq(0, qDsBean.isDel)
								.notEmptyLike(name, qDsBean.username)
								.notEmptyEq(type, qDsBean.type)
								.build()
				).orderBy(qDsBean.id.asc());

		return this.fetchPage(jpaQuery, pageDomain);
	}

	@Override
	public void deleteByDsId(String dsId) {
		blazeJPAQueryFactory.update(qDsBean).set(qDsBean.isDel, 1).where(qDsBean.dsId.eq(dsId)).execute();
	}
}
