package com.example.prac3_servlet.domain.member

class MemberRepo {

    companion object {
        private val store = HashMap<Long, Member>()
        private var sequence = 0L
    }


    fun save(member: Member): Member {
        member.id = sequence++
        store[member.id] = member
        return member
    }

    fun findById(id: Long): Member? {
        return store[id]
    }

    fun findAll(): List<Member> {
        return store.values.toList()
    }

    fun clearMap() {
        store.clear()
        sequence = 0L
    }
}