package kopring.prac12_aop.member

import kopring.prac12_aop.member.annotation.ClassAop
import kopring.prac12_aop.member.annotation.MethodAop

@ClassAop
class MemberServiceImpl : MemberService{
    override fun call(param: String): String {
        return "ok"
    }

    @MethodAop
    fun internal(param: String): String {
        return "ok"
    }
}