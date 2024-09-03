package kopring.prac11_advanced.decorator.pure

import kopring.prac11_advanced.decorator.pure.code.DecoratorClient
import kopring.prac11_advanced.decorator.pure.code.MessageDecorator
import kopring.prac11_advanced.decorator.pure.code.RealComponent
import org.junit.jupiter.api.Test

class PureDecoratorPatternTest {

    @Test
    fun pureDecorator() {
        val component = RealComponent()
        val client = DecoratorClient(component)

        client.execute()
    }

    @Test
    fun messageDecorator() {
        val component = RealComponent()
        val messageComponent = MessageDecorator(component)
        val client = DecoratorClient(messageComponent)

        client.execute()
    }
}