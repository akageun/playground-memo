package kr.geun.oss.memo.app

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

@Table(name = "memo_history")
@Entity
class MemoHistoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "memo_id")
    val memoId: Long,

    var title: String,
    var content: String?,

    @Enumerated(EnumType.STRING)
    var status: MemoStatus,

    @CreatedDate
    @Column(nullable = false, updatable = false, name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.MIN
) {
}