package kopring.prac11_advanced.strategy.code

import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {  }

class StrategyImpl: Strategy {
    override fun call() {
        logger.info { "비즈니스 로직 1번 실행" }
    }
}