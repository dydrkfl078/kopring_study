package kopring.prac11_advanced.strategy.code.state

class ConcreteStrategyB : StateStrategy {
    override fun stateCall(count: Int) : Int{
        return count * 2
    }
}