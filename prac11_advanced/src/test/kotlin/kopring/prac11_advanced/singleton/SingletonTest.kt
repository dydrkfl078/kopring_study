package kopring.prac11_advanced.singleton

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.stereotype.Service
import io.github.oshai.kotlinlogging.KotlinLogging
import org.junit.jupiter.api.Test

private val logger = KotlinLogging.logger {  }
@SpringBootTest
@Import(SingletonTestService::class)
class SingletonTest {

    @Autowired
    private lateinit var testA : SingletonTestService

    @Autowired
    private lateinit var testB : SingletonTestService

    @Autowired
    private lateinit var testC : SingletonTestService

    @Test
    fun singletonTest(){
        testA.addNum()
        testB.addNum()

        testA.displayNum() // 2
        testB.displayNum() // 2
    }

    @Test
    fun singletonTest2() {
        testA.displayNum() // 0
        testA.addNum()
        testC.displayNum() // 1
    }
}

@Service
class SingletonTestService() {
    private var num = 0

    fun addNum(){
        num++
    }

    fun displayNum() {
        logger.info { "[num] = $num" }
    }
}