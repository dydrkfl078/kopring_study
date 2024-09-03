package kopring.prac11_advanced.v1_decorator

import kopring.prac11_advanced.trace.logger.TraceLogger
import kopring.prac11_advanced.v1_decorator.decorator.DecoOrderRepositoryProxyV1
import kopring.prac11_advanced.v1_decorator.decorator.DecoOrderServiceProxyV1
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DecoratorConfig {

    @Bean
    fun decoOrderServiceV1 (trace: TraceLogger) : DecoOrderServiceV1 {
        return DecoOrderServiceProxyV1(DecoOrderServiceImplV1(decoOrderRepositoryV1(trace)),trace)
    }

    @Bean
    fun decoOrderRepositoryV1 (trace: TraceLogger) : DecoOrderRepositoryV1 {
        return DecoOrderRepositoryProxyV1(DecoOrderRepositoryImplV1(),trace)
    }
}