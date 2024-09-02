package kopring.prac11_advanced.strategy.code

import io.github.oshai.kotlinlogging.KotlinLogging

// Strategy 를 필드에 저장하는 방식.
private val logger = KotlinLogging.logger {  }
class StrategyContextV1(
    private val strategy: Strategy
) {


    fun execute() {
        logger.info { "context 실행" }
        val startTime = System.currentTimeMillis()

        strategy.call()

        val endTime = System.currentTimeMillis()

        logger.info { "context 종료 소요시간 = ${endTime - startTime}" }
    }
}