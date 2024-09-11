package kopring.prac11_advanced.factory_method.code

import io.github.oshai.kotlinlogging.KotlinLogging
private val logger = KotlinLogging.logger {  }
class BasicIdCard(private val name: String, private val email: String) : IdCard {

    override fun display(): String {
        return "name - $name, email - $email"
    }
    override fun doSomething() {
        logger.info { "Basic ID card 사용" }
    }
}