package kopring.prac11_advanced.v2

import kopring.prac11_advanced.trace.TraceId
import kopring.prac11_advanced.trace.TraceStatus
import kopring.prac11_advanced.trace.logger.TraceLoggerV1
import kopring.prac11_advanced.trace.logger.TraceLoggerV2
import org.springframework.stereotype.Service

@Service
class OrderServiceV2(private val orderRepo : OrderRepositoryV2,
                     private val logger : TraceLoggerV2,)
{

    fun save (traceId: TraceId, itemName: String) {

        var status : TraceStatus? = null

        try {
            status = logger.beginSync(traceId,"OrderServiceV1.save()")
            orderRepo.save(status.traceId, itemName)
            logger.end(status)
        } catch (e: Exception) {
            logger.exception(status!!, e)
            throw e
        }

    }
}