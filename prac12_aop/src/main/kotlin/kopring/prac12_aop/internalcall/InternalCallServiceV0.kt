package kopring.prac12_aop.internalcall

import org.springframework.stereotype.Service

import io.github.oshai.kotlinlogging.KotlinLogging
import kopring.prac12_aop.internalcall.aop.CallLogAspect
import org.springframework.context.annotation.Import

private val logger = KotlinLogging.logger {  }
@Service
@Import(CallLogAspect::class)
class InternalCallServiceV0 {

    fun externalCall(){
        internalCall() // 내부 호출 이므로 proxy 적용이 되지 않은 기본 메서드 X
    }

    fun internalCall(){}
}