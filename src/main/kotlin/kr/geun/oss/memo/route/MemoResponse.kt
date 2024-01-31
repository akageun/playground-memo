package kr.geun.oss.memo.route

import kr.geun.oss.memo.app.MemoEntity
import kr.geun.oss.memo.app.MemoStatus
import java.sql.Timestamp

data class MemoResponse(
    val memoId: Long,
    val title: String,
    val content: String?,
    val status: MemoStatus,
    val createdAt: Long,
    val updatedAt: Long,
) {

    companion object {
        fun from(entity: MemoEntity): MemoResponse = MemoResponse(
            memoId = entity.id!!,
            title = entity.title,
            content = entity.content,
            status = entity.status,
            createdAt = Timestamp.valueOf(entity.createdAt).time,
            updatedAt = Timestamp.valueOf(entity.updatedAt).time,
        )
    }
}

data class MemoDetailResponse(
    val memo: MemoResponse,
) {
}


data class MemoListResponse(
    val list: List<MemoResponse>
) {
}

