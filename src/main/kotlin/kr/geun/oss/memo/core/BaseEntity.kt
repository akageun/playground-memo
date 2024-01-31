package kr.geun.oss.memo.core

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(value = [AuditingEntityListener::class])
open class BaseEntity {

    @CreatedDate
    @Column(nullable = false, updatable = false, name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.MIN

    @LastModifiedDate
    @Column(nullable = false, name = "updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.MIN
}