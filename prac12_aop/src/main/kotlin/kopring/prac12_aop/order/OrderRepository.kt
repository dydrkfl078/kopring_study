package kopring.prac12_aop.order
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Repository

private val logger = KotlinLogging.logger {  }

@Repository
class OrderRepository {

    fun save(item: String) {
        logger.info { "OrderRepository.save 실행 item = $item" }
    }
}