package kopring.prac11_advanced.trace.logger

import io.github.oshai.kotlinlogging.KotlinLogging
import kopring.prac11_advanced.trace.TraceId
import kopring.prac11_advanced.trace.TraceStatus
import org.springframework.stereotype.Component
import kotlin.math.log

private val logger = KotlinLogging.logger {  }

@Component
class TraceLoggerV1 {

    companion object {
        const val START_PREFIX = "-->"
        const val COMPLETE_PREFIX = "<--"
        const val EX_PREFIX = "<X--"
    }

    fun begin(message: String): TraceStatus {
        val traceId = TraceId()
        val startTimeMs = System.currentTimeMillis()

        logger.info { "[${traceId.id}] ${addSpace(START_PREFIX,traceId.level)}$message" }
        return TraceStatus(traceId,startTimeMs,message)
    }

    fun end (status : TraceStatus) {
        complete(status, null)
    }

    fun exception (status : TraceStatus, e: Exception) {
        complete(status, e)
    }

    private fun complete (status: TraceStatus, e: Exception?) {
        val stopTimeMs = System.currentTimeMillis()
        val resultTimeMs = stopTimeMs - status.startTimeMs
        val traceId = status.traceId

        fun logMessage(prefix: String) = "[${traceId.id}] ${addSpace(prefix,traceId.level)}${status.message}, time = ${resultTimeMs}ms "
        e?.let { logger.info { logMessage(EX_PREFIX) + "e = $it" } }
            ?: logger.info { logMessage(COMPLETE_PREFIX) }
    }

    private fun addSpace (prefix: String, level : Int):String {
        val sb = StringBuilder()
        (0 until level).forEach { i ->
            sb.append(if (i == level - 1) "|$prefix" else "|   ")
        }

        return sb.toString()
    }
}