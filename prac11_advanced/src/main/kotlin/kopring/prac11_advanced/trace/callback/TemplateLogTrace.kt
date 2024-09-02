package kopring.prac11_advanced.trace.callback

import io.github.oshai.kotlinlogging.KotlinLogging
import kopring.prac11_advanced.trace.TraceStatus
import kopring.prac11_advanced.trace.logger.TraceLogger

class TemplateLogTrace<T>(private val trace: TraceLogger) {

    fun execute(message: String, callback: CallbackLogTrace<T>): T {
        var status: TraceStatus? = null

        try {
            status = trace.begin(message)

            val result = callback.call()

            trace.end(status)
            return result

        } catch (e: Exception) {
            trace.exception(status!!, e)
            throw e
        }

    }
}