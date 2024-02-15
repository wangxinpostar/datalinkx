package com.datalinkx.dataserver.bean.domain;

import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@ApiModel(description = "流转任务执行记录")

@Entity
@DynamicUpdate
@DynamicInsert
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "JOB_LOG")
public class JobLogBean extends StandardDomainBean {
	private static final long serialVersionUID = 1L;

//	@Id
	@Column(name = "id", nullable = false, columnDefinition = "int(11) unsigned")
	private Long id;
	@NotBlank
	@Column(name = "job_id", nullable = false, length = 35, columnDefinition = "varchar(35)")
	private String jobId;
	@ApiModelProperty(value = "0成功 1失败")
	@Column(name = "status", nullable = true, columnDefinition = "tinyint(2) unsigned")
	private Integer status;
	@NotBlank
	@Column(name = "count", nullable = false, columnDefinition = "text")
	private String count;
	@NotNull
	@Column(name = "error_msg", nullable = false, columnDefinition = "longtext")
	private String errorMsg;
	@Column(name = "start_time", nullable = false, columnDefinition = "timestamp")
	private Timestamp startTime;
	@Column(name = "end_time", nullable = false, columnDefinition = "timestamp")
	private Timestamp endTime;
	@Column(name = "cost_time", nullable = false, columnDefinition = "int(11) unsigned")
	private Integer costTime;
	@Column(name = "is_del")
	public Integer isDel = 0;
}
