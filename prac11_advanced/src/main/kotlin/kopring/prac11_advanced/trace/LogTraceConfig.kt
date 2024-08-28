package kopring.prac11_advanced.trace

import kopring.prac11_advanced.trace.logger.FieldLogTrace
import kopring.prac11_advanced.trace.logger.ThreadLocalLogTrace
import kopring.prac11_advanced.trace.logger.TraceLogger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LogTraceConfig {

    @Bean
    fun traceLogger(): TraceLogger {

        return ThreadLocalLogTrace()
    }
}