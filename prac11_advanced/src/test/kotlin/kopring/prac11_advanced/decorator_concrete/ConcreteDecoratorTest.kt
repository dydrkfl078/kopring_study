package kopring.prac11_advanced.decorator_concrete

import kopring.prac11_advanced.decorator_concrete.code.ConcreteClient
import kopring.prac11_advanced.decorator_concrete.code.ConcreteLogic
import kopring.prac11_advanced.decorator_concrete.code.TimeProxy
import org.junit.jupiter.api.Test

class ConcreteDecoratorTest {

    @Test
    fun concreteClass() {
        val concreteLogic = ConcreteLogic()
        val timeProxy = TimeProxy(concreteLogic)
        val client = ConcreteClient(timeProxy)

        client.execute()

    }
}