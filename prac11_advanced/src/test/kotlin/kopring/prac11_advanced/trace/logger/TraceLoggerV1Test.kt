package kopring.prac11_advanced.trace.logger

import org.junit.jupiter.api.Test

class TraceLoggerV1Test {

    val logger = TraceLoggerV1()

    @Test
    fun begin_end() {
        val status = logger.begin("begin_end 테스트")
        logger.end(status)
    }
    
    @Test
    fun begin_exception () {
        val status = logger.begin("begin_exception 테스트")
        logger.exception(status, IllegalStateException("예외 발생"))
    }
}