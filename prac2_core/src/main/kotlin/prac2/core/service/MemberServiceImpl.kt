package prac2.core.service

import prac2.core.member.Member
import prac2.core.repository.MemberRepo
import prac2.core.repository.MemoryMemberRepo

class MemberServiceImpl(): MemberService {

    private val memberRepo: MemberRepo = MemoryMemberRepo()

    override fun join(member: Member) {
        memberRepo.save(member)
    }

    override fun findMember(memberId: Long): Member? {
        memberRepo.findById(memberId)?.let { return it } ?:return null
    }
}