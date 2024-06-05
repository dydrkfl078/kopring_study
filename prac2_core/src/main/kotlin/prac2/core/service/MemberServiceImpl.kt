package prac2.core.service

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import prac2.core.member.Member
import prac2.core.repository.MemberRepo
import prac2.core.repository.MemoryMemberRepo

@Component
class MemberServiceImpl(private val memberRepo : MemberRepo): MemberService {

    override fun join(member: Member) {
        memberRepo.save(member)
    }

    override fun findMember(memberId: Long): Member? {
        memberRepo.findById(memberId)?.let { return it } ?: return null
    }

    // todo 테스트용
    override fun getRepo() : MemberRepo {
        return memberRepo
    }
}