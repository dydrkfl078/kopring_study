package kopring.prac11_advanced.template

import io.github.oshai.kotlinlogging.KotlinLogging
import kopring.prac11_advanced.template.code.AbstractTemplate
import org.junit.jupiter.api.Test

// V2 - 익명 클래스 적용을 통해 불필요하게 클래스를 여러개 생성하지 않아도 된다.

private val logger = KotlinLogging.logger {  }
class TemplateTestV2 {

    @Test
    fun templateMethodV2(){
        val logic1 = object : AbstractTemplate() {
            override fun call() {
                logger.info { "비즈니스 로직 1 실행" }
            }
        }

        val logic2 = object: AbstractTemplate() {
            override fun call() {
                logger.info { "비즈니스 로직 2 실행" }
            }

        }

        logic1.execute()
        logic2.execute()
    }

}