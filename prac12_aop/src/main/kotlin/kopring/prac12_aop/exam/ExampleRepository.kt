package kopring.prac12_aop.exam

import org.springframework.stereotype.Repository
import io.github.oshai.kotlinlogging.KotlinLogging
import kopring.prac12_aop.exam.annotation.Retry

private val logger = KotlinLogging.logger {  }
@Repository
class ExampleRepository {

    companion object {
        var seq = 0
    }

    @Retry(4)
    fun call(itemId : String): String {
        seq ++

        // 5번에 한번은 무조건 오류가 발생한다고 가정 --> RetryAspect 에서 Retry Annotation 으로 지정된 횟수 만큼 재시도하여 정상로직으로 처리할 것.
        if (seq % 5 == 0) {
            throw IllegalArgumentException()
        }

        return "ok"
    }
}