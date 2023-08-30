package com.sparta.post.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // JPA Entity 클래스들이 해당 추상 클래스를 상속할 경우 createdAt, modifiedAt 처럼 추상 클래스에 선언한 멤버변수를 컬럼으로 인식할 수 있습니다.
@EntityListeners(AuditingEntityListener.class)
public abstract class Timestamped {
    @CreatedDate // Entity 객체가 생성되어서 저장될 때의 시간(최초 생성 시간)
    @Column(name = "createdAt",updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @LastModifiedDate // 조회한 Entity 객체 값을 변경할 때 시간이 자동으로 저장
    @Column(name="modifiedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifiedAt;
}
