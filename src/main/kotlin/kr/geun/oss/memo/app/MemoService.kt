package kr.geun.oss.memo.app

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException
import java.time.LocalDateTime

@Service
class MemoService(
    private val memoRepository: MemoRepository,
    private val memoHistoryRepository: MemoHistoryRepository,
) {

    fun findEntity(memoId: Long): MemoEntity {
        return memoRepository.findByIdOrNull(memoId) ?: throw RuntimeException("Not Found Memo")
    }

    fun findEntities(): List<MemoEntity> {
        return memoRepository.findAll()
    }

    @Transactional
    fun create(param: MemoCreateParam): MemoEntity {

        val savedResult = memoRepository.save(
            param.makeEntity(MemoStatus.ACTIVE)
        )

        memoHistoryRepository.save(
            MemoHistoryEntity(
                memoId = savedResult.id!!,
                title = savedResult.title,
                content = savedResult.content,
                status = savedResult.status,
                createdAt = LocalDateTime.now()
            )
        )

        return savedResult;
    }

    /**
     * 입력된 값에 대해서만 Memo 값 업데이트
     * - dirty Check 이용
     * - Transactional 제거시 동작 제대로 안함.
     */
    @Transactional
    fun partialUpdate(param: MemoPartialUpdateParam): MemoEntity {
        val entity = findEntity(param.memoId)

        param.title?.let {
            entity.updateTitle(it)
        }

        param.content?.let {
            entity.updateContent(it)
        }

        param.status?.let {
            entity.updateStatus(it)
        }

        memoHistoryRepository.save(
            MemoHistoryEntity(
                memoId = entity.id!!,
                title = entity.title,
                content = entity.content,
                status = entity.status,
                createdAt = LocalDateTime.now()
            )
        )

        return entity
    }

    @Transactional
    fun fullUpdate(param: MemoFullUpdateParam): MemoEntity {
        val entity = findEntity(param.memoId)

        entity.updateTitle(param.title)

        param.content?.let {
            entity.updateContent(it)
        } ?: run {
            entity.clearContent()
        }

        entity.updateStatus(param.status)

        memoHistoryRepository.save(
            MemoHistoryEntity(
                memoId = entity.id!!,
                title = entity.title,
                content = entity.content,
                status = entity.status,
                createdAt = LocalDateTime.now()
            )
        )

        return entity
    }

    @Transactional
    fun delete(memoId: Long) {
        val entity = findEntity(memoId)

        entity.updateStatus(MemoStatus.DELETE)
        //memoRepository.deleteById(memoId)

        memoHistoryRepository.save(
            MemoHistoryEntity(
                memoId = entity.id!!,
                title = entity.title,
                content = entity.content,
                status = entity.status,
                createdAt = LocalDateTime.now()
            )
        )
    }
}