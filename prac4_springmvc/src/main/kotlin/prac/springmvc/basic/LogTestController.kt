package prac.springmvc.basic

import io.github.oshai.kotlinlogging.KotlinLogging
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

private val logger = KotlinLogging.logger {  }

@RestController
class LogTestController {

    private val log:  Logger = LoggerFactory.getLogger(this::class.java)

    @RequestMapping("/log/test")
    fun logTest():String {
        val spring = "Spring"

        // basic slf4j Logging
        log.trace(spring)
        log.debug(spring)
        log.info(spring)
        log.warn(spring)
        log.error(spring)

        // Kotlin Logging
        logger.trace { "Kotlin Logging : $spring" }
        logger.debug { "Kotlin Logging : $spring" }
        logger.info { "Kotlin Logging : $spring" }
        logger.warn { "Kotlin Logging : $spring" }
        logger.error { "Kotlin Logging : $spring" }

        return "OK"
    }
}