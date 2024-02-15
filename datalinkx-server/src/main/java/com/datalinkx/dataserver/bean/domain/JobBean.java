package com.datalinkx.dataserver.bean.domain;


import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@ApiModel(description = "流转任务")
@Entity
@DynamicUpdate
@DynamicInsert
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "JOB")
public class JobBean extends BaseEntity {
	private static final long serialVersionUID = 1L;
	@NotBlank
	@Column(name = "name", nullable = false, length = 64, columnDefinition = "char(64)")
	private String name;
	@NotBlank
	@Column(name = "job_id", nullable = false, length = 40, columnDefinition = "char(40)")
	private String jobId;
	@Column(name = "reader_ds_id", nullable = false, length = 40, columnDefinition = "char(40)")
	private String readerDsId;
	@Column(name = "writer_ds_id", nullable = false, length = 40, columnDefinition = "char(40)")
	private String writerDsId;
	@NotBlank
	@Column(name = "config", nullable = false, columnDefinition = "longtext")
	private String config;
	@Column(name = "crontab", length = 256, columnDefinition = "varchar(256)")
	private String crontab;
	@Column(name = "to_tb_id", columnDefinition = "char(40)")
	private String toTbId;
	@Column(name = "from_tb_id", columnDefinition = "char(40)")
	private String fromTbId;
	@Column(name = "xxl_id", columnDefinition = "char(40)")
	private String xxlId;
	@Column(name = "task_id", columnDefinition = "char(40)")
	private String taskId;
	@Column(name = "sync_mode", columnDefinition = "char(40)")
	private String syncMode;
	@Column(name = "`count`", columnDefinition = "text")
	private String count;
	// CREATE = 0; SYNCING = 1; NORMAL = 2; ERROR = 3; QUEUE = 4; STOP = 5
	@Column(name = "status", nullable = false, columnDefinition = "int(2)")
	private Integer status;
	@Column(name = "error_msg", nullable = true, columnDefinition = "longtext")
	private String errorMsg;
}
