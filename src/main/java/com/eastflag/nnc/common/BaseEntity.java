package com.eastflag.nnc.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @CreatedDate
    @Column(
            updatable = false
    )
    private LocalDateTime created;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updated;


    @CreatedBy
    @Column(
            updatable = false
    )
    private Integer createdBy;

    @LastModifiedBy
    @Column(insertable = false)
    private Integer updatedBy;
}
