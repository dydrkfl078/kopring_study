package kopring.prac12_aop.internalcall

import kopring.prac12_aop.internalcall.aop.CallLogAspect
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service

// 내부 호출로 인한 proxy 미적용 문제를 해결하기 위해 @Lazy 로 사용시점에 자신 객체를 주입 받도록 한다.

@Service
class InternalCallServiceV1(@Lazy private val internalCallServiceV1: InternalCallServiceV1) {

    fun externalCall(){
        internalCallServiceV1.internalCall() // 내부 호출 이므로 proxy 적용이 되지 않은 기본 메서드 X
    }

    fun internalCall(){}
}