package kopring.prac11_advanced.proxy.pure

import kopring.prac11_advanced.proxy.pure.code.CacheProxy
import kopring.prac11_advanced.proxy.pure.code.ProxyPatternClient
import kopring.prac11_advanced.proxy.pure.code.RealSubject
import org.junit.jupiter.api.Test

class PureProxyTest {

    @Test
    fun noProxyTest() {
        val realSubject = RealSubject()
        val client = ProxyPatternClient(realSubject)

        client.execute() // thread.sleep 1000L
        client.execute() // thread.sleep 1000L
        client.execute() // thread.sleep 1000L
    }

    @Test
    fun cacheProxyTest() {
        val realSubject = RealSubject()
        val cacheProxy = CacheProxy(realSubject)
        val client = ProxyPatternClient(cacheProxy)

        client.execute() // thread.sleep 1000L
        client.execute() // thread.sleep X ( 캐시 )
        client.execute() // thread.sleep X ( 캐시 )
    }
}