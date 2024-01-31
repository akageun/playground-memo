package kr.geun.oss.memo.app

import jakarta.persistence.*
import kr.geun.oss.memo.core.BaseEntity

@Table(name = "memo")
@Entity
class MemoEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var title: String,
    var content: String?,

    @Enumerated(EnumType.STRING)
    var status: MemoStatus,

    ) : BaseEntity() {

    fun updateTitle(title: String) {
        this.title = title
    }

    fun updateContent(content: String?) {
        this.content = content
    }

    fun clearContent() {
        this.content = null
    }

    fun updateStatus(memoStatus: MemoStatus) {
        status = memoStatus
    }
}