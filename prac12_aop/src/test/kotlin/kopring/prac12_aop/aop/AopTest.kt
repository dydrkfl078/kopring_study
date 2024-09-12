package kopring.prac12_aop.aop
import io.github.oshai.kotlinlogging.KotlinLogging
import kopring.prac12_aop.order.OrderRepository
import kopring.prac12_aop.order.OrderService
import kopring.prac12_aop.order.aop.AspectV1
import org.junit.jupiter.api.Test
import org.springframework.aop.support.AopUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

private val logger = KotlinLogging.logger {  }

@SpringBootTest
@Import(AspectV1::class)
class AopTest {

    @Autowired
    private lateinit var orderService : OrderService

    @Autowired
    private lateinit var orderRepository: OrderRepository

    @Test
    fun displayBean() {
        logger.info { "orderService is AOP = ${AopUtils.isAopProxy(orderService)}" }
        logger.info { "orderRepository is AOP = ${AopUtils.isAopProxy(orderRepository)}" }
    }

    @Test
    fun aspectV1doLog() {
        orderService.order("testA")
    }
}