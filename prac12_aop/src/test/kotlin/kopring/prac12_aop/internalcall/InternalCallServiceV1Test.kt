package kopring.prac12_aop.internalcall

import kopring.prac12_aop.order.aop.LogAspect
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.Lazy

@SpringBootTest
@Import(LogAspect::class)
class InternalCallServiceV1Test() {

    @Autowired
    private lateinit var internalCallServiceV1: InternalCallServiceV1

    @Test
    fun externalCall() {
        internalCallServiceV1.externalCall()
    }
}