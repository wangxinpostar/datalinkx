package com.datalinkx.dataserver.bean.domain;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 虚拟数据表
 * 
 * @author tools
 * @date 2020-02-12 14:15:20
 */
@ApiModel(description = "数据表")
@Entity
@DynamicUpdate
@DynamicInsert
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DS_TB")
public class DsTbBean extends BaseEntity {
	private static final long serialVersionUID = 1L;

//	@Id
	@NotBlank
	@ApiModelProperty(value = "tb_3444441234 ,虚拟表的id")
	@Column(name = "tb_id", nullable = false, length = 40, columnDefinition = "char(40)")
	private String tbId;
	@ApiModelProperty(value = "所属数据源")
	@Column(name = "ds_id", nullable = true, length = 40, columnDefinition = "char(40)")
	private String dsId;
	@NotBlank
	@ApiModelProperty(value = "表名，不可修改")
	@Column(name = "name", nullable = false, length = 1024, columnDefinition = "varchar(1024)")
	private String name;
	@Column(name = "status", nullable = true, columnDefinition = "tinyint(2) unsigned")
	private Integer status;
}
