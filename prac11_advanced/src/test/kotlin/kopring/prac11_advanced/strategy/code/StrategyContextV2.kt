package kopring.prac11_advanced.strategy.code

import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {  }

class StrategyContextV2 {

    fun execute(strategy: Strategy) {
        logger.info { "context 실행" }
        val startTime = System.currentTimeMillis()

        strategy.call()

        val endTime = System.currentTimeMillis()

        logger.info { "context 종료 소요시간 = ${endTime - startTime}" }
    }
}