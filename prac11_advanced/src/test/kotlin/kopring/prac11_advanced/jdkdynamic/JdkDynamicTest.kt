package kopring.prac11_advanced.jdkdynamic

import kopring.prac11_advanced.jdkdynamic.code.*
import org.junit.jupiter.api.Test
import java.lang.reflect.Proxy

class JdkDynamicTest {

    @Test
    fun serviceA () {
        val targetA = ServiceAImpl()
        val handlerA = TimeInvocationHandler(targetA)
        val proxyA = Proxy.newProxyInstance(ServiceA::class.java.classLoader, arrayOf(ServiceA::class.java), handlerA) as ServiceA

        val targetB = ServiceBImpl()
        val handlerB = TimeInvocationHandler(targetB)
        val proxyB = Proxy.newProxyInstance(ServiceB::class.java.classLoader, arrayOf(ServiceB::class.java), handlerB) as ServiceB

        proxyA.call()
        proxyB.call("테스트")

    }
}