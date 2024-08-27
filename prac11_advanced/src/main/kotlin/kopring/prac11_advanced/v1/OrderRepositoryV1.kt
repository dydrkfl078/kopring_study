package kopring.prac11_advanced.v1

import kopring.prac11_advanced.trace.TraceStatus
import kopring.prac11_advanced.trace.logger.TraceLoggerV1
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV1 (
    private val logger : TraceLoggerV1,
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

            Thread.sleep(1000L)

            logger.end(status)
        } catch (e: Exception) {
            logger.exception(status!!, e)
            throw e
        }
    }
}