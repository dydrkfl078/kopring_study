package kopring.prac11_advanced.v2

import kopring.prac11_advanced.trace.TraceId
import kopring.prac11_advanced.trace.TraceStatus
import kopring.prac11_advanced.trace.logger.TraceLoggerV1
import kopring.prac11_advanced.trace.logger.TraceLoggerV2
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV2 (
    private val logger : TraceLoggerV2,
) {

    companion object {
        const val ERROR_NAME = "예외"
    }

    fun save (traceId : TraceId, itemName: String) {

        var status : TraceStatus? = null

        try {
            status = logger.beginSync(traceId, "OrderRepositoryV1.save()")

            if (itemName == ERROR_NAME) {
                throw IllegalArgumentException("예외 발생")
            }

            Thread.sleep(1000L)

            logger.end(status)
        } catch (e: Exception) {
            logger.exception(status!!, e)
            throw e
        }
    }
}