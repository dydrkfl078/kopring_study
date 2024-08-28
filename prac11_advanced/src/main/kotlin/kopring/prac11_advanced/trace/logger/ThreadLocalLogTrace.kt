package kopring.prac11_advanced.trace.logger

import io.github.oshai.kotlinlogging.KotlinLogging
import kopring.prac11_advanced.trace.TraceId
import kopring.prac11_advanced.trace.TraceStatus
import kotlin.concurrent.getOrSet

private val logger = KotlinLogging.logger {  }
//@Component
class ThreadLocalLogTrace : TraceLogger {

    companion object {
        const val START_PREFIX = "-->"
        const val COMPLETE_PREFIX = "<--"
        const val EX_PREFIX = "<X--"
    }

    private var traceIdHolder = ThreadLocal<TraceId>()

    override fun begin(message: String): TraceStatus {
        beginSync()
        val traceId = traceIdHolder.get()
        val startTimeMs = System.currentTimeMillis()
        logger.info { "[${traceId.id}] ${addSpace(START_PREFIX,traceId.level)}$message" }
        return TraceStatus(traceId, startTimeMs, message)
    }

    private fun beginSync() {
        val traceId = traceIdHolder.get()
        if ( traceId != null) {
            traceIdHolder.set(traceId.createNextId())
        } else {
            traceIdHolder.set(TraceId())
        }
    }
    override fun end(traceStatus: TraceStatus) {
        complete(traceStatus)
    }

    override fun exception(traceStatus: TraceStatus, e: Exception) {
        complete(traceStatus,IllegalStateException())
    }

    private fun complete (status: TraceStatus, e: Exception? = null) {
        val stopTimeMs = System.currentTimeMillis()
        val resultTimeMs = stopTimeMs - status.startTimeMs
        val traceId = status.traceId

        fun logMessage(prefix: String) = "[${traceId.id}] ${addSpace(prefix,traceId.level)}${status.message}, time = ${resultTimeMs}ms "
        e?.let { logger.info { logMessage(EX_PREFIX) + "e = $it" } }
            ?: logger.info { logMessage(COMPLETE_PREFIX) }

        releaseTraceId()
    }

    private fun releaseTraceId() {
        val traceId = traceIdHolder.get()
        if (traceId.isFirstLevel()) {
            traceIdHolder.remove()
        } else {
            traceIdHolder.set(traceId.createPrevId())
        }
    }

    private fun addSpace (prefix: String, level : Int):String {
        val sb = StringBuilder()
        (0 until level).forEach { i ->
            sb.append(if (i == level - 1) "|$prefix" else "|   ")
        }

        return sb.toString()
    }
}