package kopring.prac12_aop.order

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {  }

@Service
class OrderService(private val orderRepository: OrderRepository) {

    fun order(item: String) {
        if (item.contains("ex")) {
            throw IllegalStateException()
        }

        logger.info { "OrderService.order 실행 item = $item" }
        orderRepository.save(item)
    }
}