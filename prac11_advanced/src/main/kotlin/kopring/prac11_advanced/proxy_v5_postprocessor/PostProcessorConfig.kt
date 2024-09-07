package kopring.prac11_advanced.proxy_v5_postprocessor

import org.springframework.context.annotation.Configuration
import io.github.oshai.kotlinlogging.KotlinLogging
import kopring.prac11_advanced.proxy_v4_cglib.TraceLoggerAdvice
import kopring.prac11_advanced.trace.logger.TraceLogger
import org.springframework.aop.Advisor
import org.springframework.aop.Pointcut
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.context.annotation.Bean

private val logger = KotlinLogging.logger {  }

// AutoPostProcessorConfig 사용으로 주석처리.
//@Configuration
class PostProcessorConfig {

    @Bean
    fun logTracePostProcessor(trace: TraceLogger): LogTracePostProcessor {
        val basePackage = "kopring.prac11_advanced.proxy_v5_postprocessor"
        return LogTracePostProcessor(basePackage, DefaultPointcutAdvisor(Pointcut.TRUE, TraceLoggerAdvice(trace)))
    }
}