package kopring.prac12_aop.aop.example

import kopring.prac12_aop.exam.ExampleService
import kopring.prac12_aop.exam.aop.RetryAspect
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import io.github.oshai.kotlinlogging.KotlinLogging
private val logger = KotlinLogging.logger {  }
@SpringBootTest
@Import(RetryAspect::class)
class ExampleTest {

    @Autowired
    private lateinit var exampleService: ExampleService

    @Test
    fun exampleTest(){
        (0 until 5).forEach {
            logger.info { "client request i = $it" }
            exampleService.execute("테스트$it")
        }
    }
}