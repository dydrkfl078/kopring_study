package kopring.prac11_advanced.proxy_v6_aspect

import kopring.prac11_advanced.trace.logger.TraceLogger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {  }

@Configuration
class AspectConfig {

    @Bean
    fun logTraceAspect(trace : TraceLogger) : LogTraceAspect {
        logger.info { "?????" }
        return LogTraceAspect(trace)
    }
}