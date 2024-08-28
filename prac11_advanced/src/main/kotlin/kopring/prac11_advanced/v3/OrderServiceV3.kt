package kopring.prac11_advanced.v3

import kopring.prac11_advanced.trace.TraceId
import kopring.prac11_advanced.trace.TraceStatus
import kopring.prac11_advanced.trace.logger.TraceLogger
import kopring.prac11_advanced.trace.logger.TraceLoggerV2
import org.springframework.stereotype.Service

@Service
class OrderServiceV3(private val orderRepo : OrderRepositoryV3,
                     private val logger : TraceLogger,)
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