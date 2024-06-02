package prac2.core.service

import prac2.core.member.Member

interface MemberService {
    fun join(member: Member)
    fun findMember(memberId : Long):Member?
}