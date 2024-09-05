package kopring.prac11_advanced.proxy_v2_concrete

import kopring.prac11_advanced.trace.logger.TraceLogger
import kopring.prac11_advanced.proxy_v2_concrete.proxy.OrderControllerLogProxy
import kopring.prac11_advanced.proxy_v2_concrete.proxy.OrderRepositoryLogProxy
import kopring.prac11_advanced.proxy_v2_concrete.proxy.OrderServiceLogProxy
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ConcreteConfig {

    @Bean
    fun proxyOrderControllerV2(trace: TraceLogger): ProxyOrderControllerV2 {
        val controller = ProxyOrderControllerV2(proxyOrderServiceV2(trace))
        return OrderControllerLogProxy(controller,proxyOrderServiceV2(trace),trace)
    }

    @Bean
    fun proxyOrderServiceV2(trace: TraceLogger): ProxyOrderServiceV2 {
        val service = ProxyOrderServiceV2(proxyOrderRepositoryV2(trace))
        return OrderServiceLogProxy(service,trace, OrderRepositoryLogProxy(trace,proxyOrderRepositoryV2(trace)))
    }

    @Bean
    fun proxyOrderRepositoryV2(trace: TraceLogger): ProxyOrderRepositoryV2 {
        val repository = ProxyOrderRepositoryV2()
        return OrderRepositoryLogProxy(trace,repository)
    }
}