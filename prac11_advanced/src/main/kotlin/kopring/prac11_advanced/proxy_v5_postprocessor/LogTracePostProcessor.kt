package kopring.prac11_advanced.proxy_v5_postprocessor

import org.springframework.aop.Advisor
import org.springframework.aop.framework.ProxyFactory
import org.springframework.beans.factory.config.BeanPostProcessor

import io.github.oshai.kotlinlogging.KotlinLogging
private val logger = KotlinLogging.logger {  }
class LogTracePostProcessor
    (
    private val basePackage: String,
    private val advisor : Advisor
) : BeanPostProcessor {

    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? {
        val packageName = bean::class.java.packageName
        if (!packageName.startsWith(basePackage)) {
            return bean
        }

        val proxy = ProxyFactory(bean).apply {
            addAdvisor(advisor)
        }.proxy

        logger.info { "bean class = ${bean::class}, proxy class = ${proxy::class}" }
        return proxy
    }
}