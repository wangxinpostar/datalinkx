package com.datalinkx.dataserver.bean.domain;

import java.io.Serial;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity extends StandardDomainBean {
    @Serial
    private static final long serialVersionUID = 1L;


    @Column(name = "ctime")
    public Timestamp ctime;
    @Column(name = "utime")
    public Timestamp utime;
}
