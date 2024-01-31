package kr.geun.oss.memo.app

import java.time.LocalDateTime

data class MemoCreateParam(
    val title: String,
    val content: String?,
) {
    companion object {
        fun of(title: String, content: String?) = MemoCreateParam(title, content)
    }

    fun makeEntity(status: MemoStatus): MemoEntity = MemoEntity(
        title = title,
        content = content,
        status = status,
    )
}

data class MemoPartialUpdateParam(
    val memoId: Long,
    val title: String?,
    val content: String?,
    val status: MemoStatus?,
) {

}

data class MemoFullUpdateParam(
    val memoId: Long,
    val title: String,
    val content: String?,
    val status: MemoStatus,
) {

}