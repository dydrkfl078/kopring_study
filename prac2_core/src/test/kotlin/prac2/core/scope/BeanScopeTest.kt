package prac2.core.scope

import io.kotest.matchers.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.ObjectProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope

class BeanScopeTest {
    val ac = AnnotationConfigApplicationContext(PrototypeService::class.java)

    @Test
    fun prototypeBeanScopeTest() {
        val prototypeBeanA = ac.getBean(PrototypeService::class.java)
        val prototypeBeanB = ac.getBean(PrototypeService::class.java)

        prototypeBeanA.addCount()

        prototypeBeanA.getCount() shouldNotBe prototypeBeanB.getCount()
    }
}

@Scope("prototype")
class PrototypeService(){

    private var count = 0

    fun addCount(){
        count++
    }

    fun getCount(): Int {
        return count
    }
}
