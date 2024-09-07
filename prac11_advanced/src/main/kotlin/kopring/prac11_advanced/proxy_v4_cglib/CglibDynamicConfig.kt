package kopring.prac11_advanced.proxy_v4_cglib

import kopring.prac11_advanced.trace.logger.TraceLogger
import org.springframework.aop.framework.ProxyFactory
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CglibDynamicConfig {

    companion object {
        val PATTERNS = arrayOf("request*", "save*", "order*")
    }

    private val pointcut = NameMatchMethodPointcut().apply { setMappedNames("request*","save*","order*") }
    // 메서드 내부에서는 PatternMatchUtils 를 사용하기 때문에 "*request*" 와 같이 사용할 수 있다.

    @Autowired
    private lateinit var trace : TraceLogger

    @Bean
    fun cglibOrderController(): ProxyOrderControllerV4 {
        val target = ProxyOrderControllerImplV4(cglibOrderService())
        return ProxyFactory(target).apply {
            addAdvisor(DefaultPointcutAdvisor(pointcut,TraceLoggerAdvice(trace)))
        }.proxy as ProxyOrderControllerV4
    }

    @Bean
    fun cglibOrderService() : ProxyOrderServiceV4 {
        return ProxyFactory(ProxyOrderServiceV4(cglibOrderRepository())).apply {
            addAdvisor(DefaultPointcutAdvisor(pointcut, TraceLoggerAdvice(trace)))
        }.proxy as ProxyOrderServiceV4
    }

    @Bean
    fun cglibOrderRepository() : ProxyOrderRepositoryV4 {
        return ProxyFactory(ProxyOrderRepositoryV4()).apply {
            addAdvisor(DefaultPointcutAdvisor(pointcut, TraceLoggerAdvice(trace)))
        }.proxy as ProxyOrderRepositoryV4
    }
}