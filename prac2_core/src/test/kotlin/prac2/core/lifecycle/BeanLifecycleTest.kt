package prac2.core.lifecycle

import org.junit.jupiter.api.Test
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


class BeanLifecycleTest {

    private val ac : ConfigurableApplicationContext = AnnotationConfigApplicationContext(LifeCycleConfig::class.java)

    @Test
    fun beanLifecycleTest() {
        ac.getBean(TestNetworkClient::class.java)
        ac.close()
    }
}

@Configuration
class LifeCycleConfig(){

    @Bean
    fun testNetworkClient(): TestNetworkClient {
        val netWorkA = TestNetworkClient()
        netWorkA.setUrl("http://test/lifecycle")
        return netWorkA
    }
}