package com.datalinkx.messagehub.bean.domain;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "MESSAGEHUB_TOPIC")
public class MessageHubTopicBean {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "ctime")
    protected Timestamp ctime;

    @Column(name = "utime")
    protected Timestamp utime;

    @Column(name = "is_del")
    protected Integer isDel = 0;

    // 消息topic
    @Column(name = "topic", nullable = false, columnDefinition = "varchar(255)")
    private String topic;

    //消息格式
    @Column(name = "fields")
    private String fields;

    // 消息类型
    @Column(name = "info_type", nullable = false, columnDefinition = "varchar(255)")
    private String infoType;

    // 消息描述
    @Column(name = "desc", nullable = false)
    private String desc;
}
