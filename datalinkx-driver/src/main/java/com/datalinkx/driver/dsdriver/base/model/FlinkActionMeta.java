package com.datalinkx.driver.dsdriver.base.model;

import java.util.Map;
import java.util.stream.Collectors;

import com.datalinkx.driver.dsdriver.IDsReader;
import com.datalinkx.driver.dsdriver.IDsWriter;
import com.datalinkx.driver.model.DataTransJobDetail;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;

/**
 * 执行引擎任务类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@FieldNameConstants
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlinkActionMeta extends EngineActionMeta {
    // datalinkx-server业务来源库信息
    DataTransJobDetail.Reader reader;
    // datalinkx-server业务目标库信息
    DataTransJobDetail.Writer writer;
    // 来源库driver驱动
    IDsReader dsReader;
    // 目标库driver驱动
    IDsWriter dsWriter;

    public String getReaderFieldType(String fieldName) {
        Map<String, DataTransJobDetail.Column> columnTypeMap = this.getReader().getColumns().stream()
                .collect(Collectors.toMap(DataTransJobDetail.Column::getName, x -> x));
        return columnTypeMap.get(fieldName).getType();
    }
}
