package prac2.core.member

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import prac2.core.AppConfig
import prac2.core.service.MemberService

class MemberServiceTest {

    companion object {
        val ac = AnnotationConfigApplicationContext(AppConfig::class.java)
        val memberService = ac.getBean("memberService") as MemberService
    }

    @Test
    fun join(){
        // given
        val member = Member(1L, "Test Member", Grade.VIP )

        // when
        memberService.join(member)

        //then
        member.getId() shouldBe memberService.findMember(member.getId())?.getId()
    }
}