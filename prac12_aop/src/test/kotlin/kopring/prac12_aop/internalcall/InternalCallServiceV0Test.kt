package kopring.prac12_aop.internalcall

import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class InternalCallServiceV0Test {

    @Autowired
    private lateinit var service: InternalCallServiceV0

    @Test
    fun externalCall() {
        service.externalCall()
    }

    @Test
    fun internalCall() {
        service.internalCall()
    }
}