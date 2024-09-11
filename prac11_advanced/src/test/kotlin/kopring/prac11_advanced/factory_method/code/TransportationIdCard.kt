package kopring.prac11_advanced.factory_method.code

import io.github.oshai.kotlinlogging.KotlinLogging
private val logger = KotlinLogging.logger {  }
class TransportationIdCard(private val name: String, private val email: String) : IdCard {

    override fun display(): String {
        return "name - $name, email - $email"
    }
    override fun doSomething() {
        logger.info { "교통카드 지원 ID card 사용" }
    }
}