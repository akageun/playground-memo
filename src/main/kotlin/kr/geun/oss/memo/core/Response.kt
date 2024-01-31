package kr.geun.oss.memo.core

import com.fasterxml.jackson.annotation.JsonInclude

data class Response<T>(

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val result: T? = null,
    val message: String? = null,
) {

    companion object {
        fun <T> success(result: T? = null) = Response(
            result = result,
            message = null,
        )

    }

}