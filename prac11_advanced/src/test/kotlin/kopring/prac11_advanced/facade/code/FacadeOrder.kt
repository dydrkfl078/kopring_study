package kopring.prac11_advanced.facade.code

import io.github.oshai.kotlinlogging.KotlinLogging
private val logger = KotlinLogging.logger {  }
class FacadeOrder {
    fun startOrder(){
        logger.info { "startOrder!" }
    }
}