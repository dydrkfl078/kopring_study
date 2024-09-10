package kopring.prac11_advanced.strategy.code.state

import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {  }
class StrategyContextV3 {

    private var count = 0

    fun execute(strategy: StateStrategy) {
        logger.info { "context 실행" }
        val startTime = System.currentTimeMillis()

        count = strategy.stateCall(count)

        logger.info { "현재 count = $count" }

        val endTime = System.currentTimeMillis()

        logger.info { "context 종료 소요시간 = ${endTime - startTime}" }
    }
}