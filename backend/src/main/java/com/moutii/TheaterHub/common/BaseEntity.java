package com.moutii.TheaterHub.common;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "CREATED_BY",nullable = false,updatable = false)
    @CreatedBy
    private String createdBy;

    @Column(name = "CREATED_AT",nullable = false,updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "LAST_MODIFIED_BY",insertable = false)
    @LastModifiedBy
    private String lastModifiedBy;

    @Column(name = "LAST_MODIFIED_AT",insertable = false)
    @LastModifiedDate
    private LocalDateTime lastModifiedAt;


}
