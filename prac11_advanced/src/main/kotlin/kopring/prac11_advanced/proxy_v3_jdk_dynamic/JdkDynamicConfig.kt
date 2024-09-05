package kopring.prac11_advanced.proxy_v3_jdk_dynamic

import kopring.prac11_advanced.trace.logger.TraceLogger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.lang.reflect.Proxy

@Configuration
class JdkDynamicConfig {

    companion object {
        val PATTERNS = arrayOf("request*", "save*", "order*")
    }

    @Autowired
    private lateinit var trace : TraceLogger

    @Bean
    fun jdkDynamicOrderRepository(): ProxyOrderRepositoryV3 {
        val target = ProxyOrderRepositoryImplV3()
        val handler = TraceLoggerHandler(target,trace, PATTERNS)
        val proxy = Proxy.newProxyInstance(ProxyOrderRepositoryV3::class.java.classLoader, arrayOf(ProxyOrderRepositoryV3::class.java),handler) as ProxyOrderRepositoryV3
        return proxy
    }

    @Bean
    fun jdkDynamicOrderService(): ProxyOrderServiceV3 {
        val target = ProxyOrderServiceImplV3(jdkDynamicOrderRepository())
        val handler = TraceLoggerHandler(target, trace, PATTERNS)
        val proxy = Proxy.newProxyInstance(ProxyOrderServiceV3::class.java.classLoader, arrayOf(ProxyOrderServiceV3::class.java), handler) as ProxyOrderServiceV3
        return proxy
    }

    @Bean
    fun jdkDynamicOrderController(): ProxyOrderControllerV3 {
        val target = ProxyOrderControllerImplV3(jdkDynamicOrderService())
        val handler = TraceLoggerHandler(target, trace, PATTERNS )
        val proxy = Proxy.newProxyInstance(ProxyOrderControllerV3::class.java.classLoader, arrayOf(ProxyOrderControllerV3::class.java), handler) as ProxyOrderControllerV3
        return proxy
    }
}