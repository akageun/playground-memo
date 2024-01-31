package kr.geun.oss.memo.app

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException

@Service
class MemoService(
    private val memoRepository: MemoRepository,
) {

    fun findEntity(memoId: Long): MemoEntity {
        return memoRepository.findByIdOrNull(memoId) ?: throw RuntimeException("Not Found Memo")
    }

    fun findEntities(): List<MemoEntity> {
        return memoRepository.findAll()
    }

    @Transactional
    fun create(param: MemoCreateParam): MemoEntity {
        return memoRepository.save(
            param.makeEntity(MemoStatus.ACTIVE)
        )
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

        return entity
    }

    @Transactional
    fun delete(memoId: Long) {
        val entity = findEntity(memoId)

        entity.updateStatus(MemoStatus.DELETE)
        //memoRepository.deleteById(memoId)
    }
}