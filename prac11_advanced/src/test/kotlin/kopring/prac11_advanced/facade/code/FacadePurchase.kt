package kopring.prac11_advanced.facade.code

import io.github.oshai.kotlinlogging.KotlinLogging
private val logger = KotlinLogging.logger {  }
class FacadePurchase {
    fun startPurchase() {
        logger.info { "start purchase!" }
    }
}