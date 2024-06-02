package prac2.core.repository

import prac2.core.member.Member

class MemoryMemberRepo(): MemberRepo {

    companion object {
        private val store = HashMap<Long, Member>()
    }

    override fun save(member: Member) {
        store[member.getId()] = member
    }

    override fun findById(memberId: Long): Member? {
        store[memberId]?.let { return it } ?:return null
    }
}