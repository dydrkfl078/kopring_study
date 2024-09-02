package kopring.prac11_advanced.v3

import kopring.prac11_advanced.trace.TraceStatus
import kopring.prac11_advanced.trace.logger.TraceLogger
import kopring.prac11_advanced.trace.template.AbstractLogTrace
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV4 (
    private val logger : TraceLogger,
) {

    companion object {
        const val ERROR_NAME = "예외"
    }

    fun save (itemName: String) {
        val template = object : AbstractLogTrace<Unit>(logger) {
            override fun call() {

                if (itemName.contains(ERROR_NAME)) {
                    throw IllegalArgumentException("예외 발생")
                }

                Thread.sleep(3000L)
            }
        }

        template.execute("OrderRepositoryV4.save()")
    }
}