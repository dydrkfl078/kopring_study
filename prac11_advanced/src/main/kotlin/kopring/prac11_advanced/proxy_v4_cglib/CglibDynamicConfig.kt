package kopring.prac11_advanced.proxy_v4_cglib

import kopring.prac11_advanced.trace.logger.TraceLogger
import org.springframework.aop.framework.ProxyFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cglib.proxy.Enhancer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CglibDynamicConfig {

    companion object {
        val PATTERNS = arrayOf("request*", "save*", "order*")
    }

    @Autowired
    private lateinit var trace : TraceLogger

    @Bean
    fun cglibOrderController(): ProxyOrderControllerV4 {
        val target = ProxyOrderControllerImplV4(cglibOrderService())
        return ProxyFactory(target).apply {
            addAdvice(TraceLoggerHandlerCglib(trace, PATTERNS))
        }.proxy as ProxyOrderControllerV4
    }

    @Bean
    fun cglibOrderService() : ProxyOrderServiceV4 {
        return ProxyFactory(ProxyOrderServiceV4(cglibOrderRepository())).apply {
            addAdvice(TraceLoggerHandlerCglib(trace, PATTERNS))
        }.proxy as ProxyOrderServiceV4
    }

    @Bean
    fun cglibOrderRepository() : ProxyOrderRepositoryV4 {
        return ProxyFactory(ProxyOrderRepositoryV4()).apply {
            addAdvice(TraceLoggerHandlerCglib(trace, PATTERNS))
        }.proxy as ProxyOrderRepositoryV4
    }
}