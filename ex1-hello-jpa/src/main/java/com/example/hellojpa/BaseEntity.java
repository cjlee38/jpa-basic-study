package com.example.hellojpa;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass // 매핑 정보만 받는 슈퍼클래스.
public abstract class BaseEntity {

    private String createdBy;
    private LocalDateTime createdAt;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;

}
