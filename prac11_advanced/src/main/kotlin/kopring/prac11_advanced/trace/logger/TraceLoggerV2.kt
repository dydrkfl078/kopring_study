package kopring.prac11_advanced.trace.logger

import io.github.oshai.kotlinlogging.KotlinLogging
import kopring.prac11_advanced.trace.TraceId
import kopring.prac11_advanced.trace.TraceStatus
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {  }

// V2 - beginSync 메서드를 추가하여 TraceId 를 파라미터로 넘겨서, 이전 트레이스 정보를 파라미터로 넘거준다.
// -> 테스트 코드 등 controller 외에 beginSync 를 사용하려면 null 로 넘겨주는 등 처리가 복잡해짐.
// -> repository, service 가 interface 라면 메서드를 전부 다 바꿔주어야 함

@Component
class TraceLoggerV2 {

    companion object {
        const val START_PREFIX = "-->"
        const val COMPLETE_PREFIX = "<--"
        const val EX_PREFIX = "<X--"
    }

    fun begin(message: String): TraceStatus {
        val traceId = TraceId()
        val startTimeMs = System.currentTimeMillis()

        logger.info { "[${traceId.id}] ${addSpace(START_PREFIX,traceId.level)}$message" }
        return TraceStatus(traceId, startTimeMs, message)
    }

    fun beginSync(prevTraceId: TraceId, message: String) : TraceStatus {
        val nextId = prevTraceId.createNextId()
        val startTimeMs = System.currentTimeMillis()

        logger.info { "[${nextId.id}] ${addSpace(START_PREFIX,nextId.level)}$message" }
        return TraceStatus(nextId, startTimeMs, message)
        
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