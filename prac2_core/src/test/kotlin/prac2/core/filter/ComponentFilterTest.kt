package prac2.core.filter

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.nulls.shouldNotBeNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScan.Filter
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType

class ComponentFilterTest {

    private val ac = AnnotationConfigApplicationContext(TestFilterAppConfig::class.java)

    @Test
    @DisplayName("Include 필터로 설정된 BeanA는 스프링 컨테이너에 들어갔다.")
    fun includeFilter(){
        ac.getBean("beanA", BeanA::class.java).shouldNotBeNull()
    }

    @Test
    @DisplayName("Exclude 필터로 설정된 BeanB는 스프링 컨테이너에 들어가지 않는다.")
    fun excludeFilter(){
        shouldThrow<NoSuchBeanDefinitionException> {
            ac.getBean("beanB", BeanB::class.java)
        }
    }
}

@Configuration
@ComponentScan (
    includeFilters = [Filter(type = FilterType.ANNOTATION, classes = [MyIncludeComponent::class])],
    excludeFilters = [Filter(type = FilterType.ANNOTATION, classes = [MyExcludeComponent::class])]
)
class TestFilterAppConfig(){

}