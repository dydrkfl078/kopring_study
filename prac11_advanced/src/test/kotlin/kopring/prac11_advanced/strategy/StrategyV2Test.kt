package kopring.prac11_advanced.strategy

import io.github.oshai.kotlinlogging.KotlinLogging
import kopring.prac11_advanced.strategy.code.Strategy
import kopring.prac11_advanced.strategy.code.StrategyContextV1
import kopring.prac11_advanced.strategy.code.StrategyContextV2
import kopring.prac11_advanced.strategy.code.StrategyImpl
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

private val logger = KotlinLogging.logger {  }
class StrategyV2Test {

    private val strategy = StrategyImpl()

    @Test
    @DisplayName("전략패턴 V1 - 구현 클래스를 사용한다.")
    fun strategyTestV1() {
        val strategy1 = StrategyContextV2()

        strategy1.execute(strategy)
    }

    @Test
    @DisplayName("전략패턴 V2 - 익명 클래스를 사용한다.")
    fun strategyTestV2() {
        val strategy2 = StrategyContextV2()
        strategy2.execute(object : Strategy {
            override fun call() {
                logger.info { "비즈니스 로직 2번 실행" }
            }
        })
    }

    @Test
    @DisplayName("전략패턴 V3 - 함수형 인터페이스 - 람다를 사용한다.")
    fun strategyTestV3(){
        val strategy3 = StrategyContextV2()
        strategy3.execute { logger.info { "비즈니스 로직 3번 실행" } }

        val strategy4 = StrategyContextV2()
        strategy4.execute{ logger.info { "비즈니스 로직 4번 실행" } }
    }
}