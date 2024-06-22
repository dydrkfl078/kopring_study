package kopring.prac6_login.domain.member

import org.springframework.stereotype.Repository


@Repository
class MemberRepo {

    companion object {
        val store = hashMapOf<Long, Member>()
        var sequence = 0L
    }

    fun save(member: Member): Member {
        member.id = ++sequence
        store[member.id!!] = member

        return member
    }

    fun findById(id: Long): Member? {
        return store[id]
    }

    fun findByLoginId(id: String): Member? {
        val memberList = findAll()
        return memberList.find { m -> m.loginId == id }
    }

    fun findAll(): List<Member> {
        return store.values.toList()
    }

    fun clearStore() {
        store.clear()
    }
}