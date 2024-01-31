package kr.geun.oss.memo.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@EntityScan("kr.geun.oss.memo")
@EnableJpaRepositories("kr.geun.oss.memo")
@EnableJpaAuditing
@Configuration
class JpaConfig(
) {

}