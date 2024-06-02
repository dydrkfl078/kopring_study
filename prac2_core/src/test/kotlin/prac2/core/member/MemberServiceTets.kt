package prac2.core.member

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import prac2.core.repository.MemoryMemberRepo
import prac2.core.service.MemberService
import prac2.core.service.MemberServiceImpl

class MemberServiceTets {

    companion object {
        val memberService = MemberServiceImpl()
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