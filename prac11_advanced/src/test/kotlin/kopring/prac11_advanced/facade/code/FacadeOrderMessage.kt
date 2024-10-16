package kopring.prac11_advanced.facade.code

import io.github.oshai.kotlinlogging.KotlinLogging
private val logger = KotlinLogging.logger {  }
class FacadeOrderMessage {
    fun sendNewOrderMessage(){
        logger.info { "send new order message!" }
    }
}