package kopring.prac11_advanced.decorator_concrete.code

class ConcreteClient(private val concreteLogic : ConcreteLogic) {

    fun execute() {
        concreteLogic.operation()
    }
}