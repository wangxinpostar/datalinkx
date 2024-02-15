package com.datalinkx.dataserver.bean.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@DynamicInsert
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DS")
public class DsBean extends BaseEntity {

	private static final long serialVersionUID = 1L;

//	@Id
	@Column(name = "ds_id", nullable = true, length = 35, columnDefinition = "char(35)")
	private String dsId;
	@Column(name = "`name`", nullable = true, length = 64, columnDefinition = "varchar(64)")
	private String name;
	@Column(name = "type", nullable = true, columnDefinition = "int(11)")
	private Integer type;
	@Column(name = "`host`", nullable = true, length = 64, columnDefinition = "varchar(64)")
	private String host;
	@Column(name = "`port`", nullable = true, columnDefinition = "int(11)")
	private Integer port;
	@Column(name = "username", nullable = true, length = 128, columnDefinition = "varchar(128)")
	private String username;
	@Column(name = "`password`", nullable = true, length = 256, columnDefinition = "varchar(256)")
	private String password;
	@Column(name = "config", nullable = true, length = 65535, columnDefinition = "text")
	private String config;
	@Column(name = "`database`", nullable = true, length = 64, columnDefinition = "varchar(64)")
	private String database;
	@Column(name = "`schema`", nullable = true, length = 64, columnDefinition = "varchar(64)")
	private String schema;
}
