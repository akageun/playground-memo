package kr.geun.oss.memo.app

import org.springframework.data.jpa.repository.JpaRepository

interface MemoHistoryRepository : JpaRepository<MemoHistoryEntity, Long?> {
}