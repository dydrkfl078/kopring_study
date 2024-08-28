package kopring.prac11_advanced.v3

import kopring.prac11_advanced.trace.TraceId
import kopring.prac11_advanced.trace.TraceStatus
import kopring.prac11_advanced.trace.logger.TraceLogger
import kopring.prac11_advanced.trace.logger.TraceLoggerV2
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV3 (
    private val logger : TraceLogger,
) {

    companion object {
        const val ERROR_NAME = "예외"
    }

    fun save (itemName: String) {

        var status : TraceStatus? = null

        try {
            status = logger.begin("OrderRepositoryV1.save()")

            if (itemName == ERROR_NAME) {
                throw IllegalArgumentException("예외 발생")
            }

            Thread.sleep(3000L)

            logger.end(status)

        } catch (e: Exception) {
            logger.exception(status!!, e)
            throw e
        }
    }
}