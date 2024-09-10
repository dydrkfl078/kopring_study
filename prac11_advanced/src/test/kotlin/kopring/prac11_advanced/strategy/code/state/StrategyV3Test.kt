package kopring.prac11_advanced.strategy.code.state

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {  }
class StrategyV3Test {

    private val strategyA = ConcreteStrategyA()
    private val strategyB = ConcreteStrategyB()

    @Test
    @DisplayName("전략패턴 V3 - 런타임 중 전략 알고리즘 변경에 자유롭다")
    fun stateStrategyTestV1() {
        val strategy = StrategyContextV3()

        strategy.execute(strategyA)

        strategy.execute(strategyB)

        strategy.execute(strategyA)

        strategy.execute(strategyB)
        strategy.execute(strategyB)

        strategy.execute(strategyA)

    }
}