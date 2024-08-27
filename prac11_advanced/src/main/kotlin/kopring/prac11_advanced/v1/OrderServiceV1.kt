package kopring.prac11_advanced.v1

import kopring.prac11_advanced.trace.TraceStatus
import kopring.prac11_advanced.trace.logger.TraceLoggerV1
import org.springframework.stereotype.Service

@Service
class OrderServiceV1(private val orderRepo : OrderRepositoryV1,
                     private val logger : TraceLoggerV1,)
{

    fun save (itemName: String) {

        var status : TraceStatus? = null

        try {
            status = logger.begin("OrderServiceV1.save()")
            orderRepo.save(itemName)
            logger.end(status)
        } catch (e: Exception) {
            logger.exception(status!!, e)
            throw e
        }

    }
}