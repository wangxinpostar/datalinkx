package com.datalinkx.driver.dsdriver.base.model;

import com.datalinkx.compute.connector.jdbc.PluginNode;
import com.datalinkx.compute.connector.model.TransformNode;
import com.datalinkx.driver.dsdriver.IDsReader;
import com.datalinkx.driver.dsdriver.IDsWriter;
import com.datalinkx.driver.model.DataTransJobDetail;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@FieldNameConstants
@JsonIgnoreProperties(ignoreUnknown = true)
public class SeatunnelActionMeta extends EngineActionMeta {
    IDsWriter writerDsDriver;
    DataTransJobDetail.Writer writer;
    private PluginNode sourceInfo;
    private PluginNode sinkInfo;
    // 计算任务的中间节点
    private TransformNode transformInfo;
    private String jobMode;
    private Integer parallelism;
    Integer cover;
}