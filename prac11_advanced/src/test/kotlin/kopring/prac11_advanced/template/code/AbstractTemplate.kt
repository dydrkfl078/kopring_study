package kopring.prac11_advanced.template.code

import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {  }

abstract class AbstractTemplate {

    fun execute(){
        val startTime = System.currentTimeMillis()

        call()

        val endTime = System.currentTimeMillis()

        logger.info { "result time = ${endTime - startTime}" }

    }

    protected abstract fun call()
}