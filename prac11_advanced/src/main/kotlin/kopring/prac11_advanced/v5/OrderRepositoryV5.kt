package kopring.prac11_advanced.v5

import kopring.prac11_advanced.trace.callback.TemplateLogTrace
import kopring.prac11_advanced.trace.logger.TraceLogger
import kopring.prac11_advanced.trace.template.AbstractLogTrace
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV5(
    private val logger: TraceLogger,
) {

    companion object {
        const val ERROR_NAME = "예외"
    }

    private val template = TemplateLogTrace<Unit>(logger)

    fun save(itemName: String) {
        template.execute("OrderRepositoryV4.save()") {
            if (itemName.contains(ERROR_NAME)) {
                throw IllegalArgumentException("예외 발생")
            }

            Thread.sleep(3000L)
        }
    }
}