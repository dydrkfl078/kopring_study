package kopring.prac11_advanced.factory_method

import kopring.prac11_advanced.factory_method.code.BasicIdCardFactory
import kopring.prac11_advanced.factory_method.code.TransportationIdCardFactory
import org.junit.jupiter.api.Test

class TemplateMethodTest {

    @Test
    fun templateMethodTest() {
        val userAIdCard = BasicIdCardFactory().orderIdCard("A", "userA@kotlin.com")
        val userBIdCard = TransportationIdCardFactory().orderIdCard("B","userB@kotlin.com")

        userAIdCard.doSomething()
        userBIdCard.doSomething()
    }
}