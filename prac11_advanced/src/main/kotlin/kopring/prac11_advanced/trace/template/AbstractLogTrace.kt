package kopring.prac11_advanced.trace.template

import kopring.prac11_advanced.trace.TraceStatus
import kopring.prac11_advanced.trace.logger.TraceLogger

abstract class AbstractLogTrace<T> (private val trace : TraceLogger) {

    fun execute(message: String) : T {
        var status : TraceStatus? = null
        try {
            status = trace.begin(message)

            val result : T = call()

            trace.end(status)
            return result
        } catch (e: Exception) {
            trace.exception(status!!,e)
            throw e
        }
    }

    protected abstract fun call(): T

}