package kopring.prac11_advanced.factory_method.code

import io.github.oshai.kotlinlogging.KotlinLogging
private val logger = KotlinLogging.logger {  }
abstract class IdCardFactory {

    fun orderIdCard(name: String, email: String): IdCard {
        val idCard = createIdCard(name, email)

        registration(idCard)
        return idCard
    }

    protected abstract fun createIdCard(name: String, email: String): IdCard

    private fun registration(idCard: IdCard){
        logger.info { "${idCard.display()} id card DB 등록" }
    }
}