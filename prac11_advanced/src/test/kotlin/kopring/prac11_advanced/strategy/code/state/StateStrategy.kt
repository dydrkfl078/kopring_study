package kopring.prac11_advanced.strategy.code.state

interface StateStrategy {
    fun stateCall(count: Int): Int
}