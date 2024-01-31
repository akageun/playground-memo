package kr.geun.oss.memo.route

import jakarta.validation.constraints.NotBlank
import kr.geun.oss.memo.app.MemoCreateParam
import kr.geun.oss.memo.app.MemoFullUpdateParam
import kr.geun.oss.memo.app.MemoPartialUpdateParam
import kr.geun.oss.memo.app.MemoStatus

data class MemoListRequest(
    val cursor: String?,
    val limit: Int? = 10,
) {

}

//data class MemoDetailRequest(
//
//) {
//
//}

data class MemoCreateRequest(
    @field:NotBlank
    val title: String,

    val content: String?,
) {

    fun makeParam(): MemoCreateParam = MemoCreateParam.of(title, content)
}

data class MemoPartialUpdateRequest(
    val title: String?,
    val content: String?,
    val status: MemoStatus?,
) {

    fun isValid(): Boolean {
        if (title == null && content == null || status == null) {
            return false
        }

        return true
    }

    fun makeParam(memoId: Long): MemoPartialUpdateParam = MemoPartialUpdateParam(
        memoId = memoId,
        title = title,
        content = content,
        status = status,
    )
}

data class MemoFullUpdateRequest(
    @field:NotBlank
    val title: String,

    val content: String?,
    val status: MemoStatus,
) {
    fun makeParam(memoId: Long): MemoFullUpdateParam = MemoFullUpdateParam(
        memoId = memoId,
        title = title,
        content = content,
        status = status,
    )
}
