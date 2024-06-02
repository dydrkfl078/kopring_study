package prac2.core.repository

import prac2.core.member.Member

interface MemberRepo {

    fun save(member:Member)
    fun findById(memberId: Long): Member?
}