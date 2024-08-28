package kopring.prac11_advanced.trace.logger

import kopring.prac11_advanced.trace.TraceId
import kopring.prac11_advanced.trace.TraceStatus

interface TraceLogger {

    fun begin(message: String): TraceStatus

    fun end(traceStatus: TraceStatus)

    fun exception(traceStatus: TraceStatus, e : Exception)
}