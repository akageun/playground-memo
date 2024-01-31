package kr.geun.oss.memo.route

import jakarta.validation.Valid
import kr.geun.oss.memo.app.MemoService
import kr.geun.oss.memo.core.Response
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Validated
@RestController
@RequestMapping("/memo")
class MemoApi(
    private val memoService: MemoService,
) {

    @GetMapping("")
    fun memoList(
        request: MemoListRequest,
    ): ResponseEntity<Response<MemoListResponse>> {

        val list = memoService.findEntities().map {
            MemoResponse.from(it)
        }

        return ResponseEntity.ok(
            Response.success(
                MemoListResponse(
                    list = list,
                )
            )
        )
    }

    @GetMapping("/{memoId}")
    fun memoDetail(
        @PathVariable memoId: Long,
    ): ResponseEntity<Response<MemoDetailResponse>> {

        val entity = memoService.findEntity(memoId)

        return ResponseEntity.ok(
            Response.success(
                MemoDetailResponse(
                    memo = MemoResponse.from(entity),
                )
            )
        )
    }

    @PostMapping("")
    fun createMemo(
        @RequestBody @Valid request: MemoCreateRequest,
    ): ResponseEntity<Response<MemoResponse>> {

        val entity = memoService.create(request.makeParam())

        return ResponseEntity.ok(
            Response.success(MemoResponse.from(entity))
        )
    }

    @PutMapping("/{memoId}")
    fun memoFullUpdate(
        @PathVariable memoId: Long,
        @RequestBody @Valid request: MemoFullUpdateRequest,
    ): ResponseEntity<Response<MemoResponse>> {

        val entity = memoService.fullUpdate(request.makeParam(memoId))

        return ResponseEntity.ok(
            Response.success(MemoResponse.from(entity))
        )
    }

    @PatchMapping("/{memoId}")
    fun memoPartialUpdate(
        @PathVariable memoId: Long,
        @RequestBody @Valid request: MemoPartialUpdateRequest,
    ): ResponseEntity<Response<MemoResponse>> {

        val memoResult = memoService.partialUpdate(request.makeParam(memoId))

        return ResponseEntity.ok(
            Response.success(MemoResponse.from(memoResult))
        )
    }

    @DeleteMapping("/{memoId}")
    fun deleteMemo(
        @PathVariable memoId: Long,
    ): ResponseEntity<Response<Void>> {

        memoService.delete(memoId)

        return ResponseEntity.ok(
            Response.success()
        )
    }
}