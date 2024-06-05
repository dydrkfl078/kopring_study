package prac2.core.service

import prac2.core.member.Member
import prac2.core.repository.MemberRepo

interface MemberService {
    fun join(member: Member)
    fun findMember(memberId : Long):Member?

    // todo 테스트용
    fun getRepo(): MemberRepo
}