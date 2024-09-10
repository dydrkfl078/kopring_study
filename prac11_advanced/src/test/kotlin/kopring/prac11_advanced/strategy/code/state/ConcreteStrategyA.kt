package kopring.prac11_advanced.strategy.code.state

class ConcreteStrategyA : StateStrategy {
    override fun stateCall(count: Int): Int {
        return count + 1
    }
}