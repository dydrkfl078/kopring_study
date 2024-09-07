package kopring.prac11_advanced.postprocessor

import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Configuration
import io.github.oshai.kotlinlogging.KotlinLogging
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.should
import io.kotest.matchers.types.shouldBeSameInstanceAs
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.beans.factory.getBean
import org.springframework.context.annotation.Bean

private val logger = KotlinLogging.logger {  }
class PostProcessorBasicTest {

    @Test
    fun basicConfig() {
        val applicationContext = AnnotationConfigApplicationContext(BasicConfig::class.java)

        val testBeanA = applicationContext.getBean("testBeanA", TestBeanB::class) as TestBeanB

        testBeanA.hello()

        shouldThrow<NoSuchBeanDefinitionException> {
            applicationContext.getBean(TestBeanA::class)
        }

    }
}

@Configuration
class BasicConfig() {

    @Bean
    fun testBeanA(): TestBeanA {
        return TestBeanA()
    }

    @Bean
    fun myPostProcessor(): MyPostProcessor {
        return MyPostProcessor()
    }
}

class TestBeanA() {
    fun hello() {
        logger.info { "TestBean(A).hello !" }
    }
}

class TestBeanB() {
    fun hello() {
        logger.info { "TestBean(B).hello !" }
    }
}

class MyPostProcessor(): BeanPostProcessor {
    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? {
        return if (bean is TestBeanA) TestBeanB() else bean
    }
}