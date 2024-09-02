package kopring.prac11_advanced.v3

import kopring.prac11_advanced.trace.TraceStatus
import kopring.prac11_advanced.trace.logger.TraceLogger
import org.springframework.stereotype.Service

@Service
class OrderServiceV3(private val orderRepo : OrderRepositoryV3,
                     private val logger : TraceLogger,)
{

    fun save (itemName: String) {

        var status : TraceStatus? = null

        try {
            status = logger.begin("OrderServiceV3.save()")
            orderRepo.save(itemName)
            logger.end(status)
        } catch (e: Exception) {
            logger.exception(status!!, e)
            throw e
        }

    }
}