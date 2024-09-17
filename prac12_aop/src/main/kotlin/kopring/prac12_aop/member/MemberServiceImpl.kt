package kopring.prac12_aop.member

import kopring.prac12_aop.member.annotation.ClassAop
import kopring.prac12_aop.member.annotation.MethodAop
import org.springframework.stereotype.Service

@ClassAop
@Service
class MemberServiceImpl : MemberService{
    override fun call(param: String): String {
        return "ok"
    }

    @MethodAop(value = "test value")
    fun internal(param: String): String {
        return "ok"
    }
}