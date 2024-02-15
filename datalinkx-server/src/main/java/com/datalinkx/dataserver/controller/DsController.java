package com.datalinkx.dataserver.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.datalinkx.common.result.WebResult;
import com.datalinkx.dataserver.bean.domain.DsBean;
import com.datalinkx.dataserver.bean.vo.PageVo;
import com.datalinkx.dataserver.controller.form.DsForm;
import com.datalinkx.dataserver.service.impl.DsService;
import com.datalinkx.driver.dsdriver.base.model.TableField;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Slf4j
@RestController
@RequestMapping("/api/ds")
@Api(tags = "ds")
public class DsController {

	@Autowired
	private DsService dsService;


	@ApiOperation("数据源分页查询")
	@GetMapping("/page")
	public PageVo<List<DsBean>> dsPage(DsForm.DataSourcePageForm dataSourcePageForm) {
		return dsService.dsPage(dataSourcePageForm);
	}

	@ApiOperation("数据源列表查询")
	@GetMapping("/list")
	public WebResult<List<DsBean>> list() {
		return WebResult.of(dsService.list());
	}


	@ApiOperation("已注册的数据源数量类型分布")
	@GetMapping("/group")
	public WebResult<Map> group() {
		return WebResult.of(
				dsService.list().stream().collect(Collectors.groupingBy(DsBean::getType,  Collectors.counting()))
		);
	}


	@ApiOperation("数据源详情")
	@GetMapping("/info/{dsId}")
	public WebResult<DsBean> info(@PathVariable String dsId) {
		return WebResult.of(dsService.info(dsId));
	}

	@ApiOperation("数据源编辑")
	@PostMapping("/modify")
	public void modify(@RequestBody DsForm.DsCreateForm form) {
		this.dsService.modify(form);
	}

	@ApiOperation("数据源创建")
	@PostMapping("/create")
	public WebResult<String> create(@RequestBody DsForm.DsCreateForm form) throws UnsupportedEncodingException {
		return WebResult.of(dsService.create(form));
	}

	@ApiOperation("数据源删除")
	@PostMapping("/delete/{dsId}")
	public void del(@PathVariable String dsId) {
		dsService.del(dsId);
	}

	@ApiOperation("获取数据源下数据表")
	@GetMapping("/tables/{dsId}")
	public WebResult<List<String>> fetchTables(@PathVariable String dsId) {
		return WebResult.of(dsService.fetchTables(dsId));
	}

	@ApiOperation("获取指定数据源下数据表的字段信息")
	@GetMapping("/field/info")
	public WebResult<List<TableField>> tbInfo(String dsId, String name) {
		return WebResult.of(dsService.fetchFields(dsId, name));
	}
}
