package practice_thymeleaf.kopring_prac1.repository

import practice_thymeleaf.kopring_prac1.domain.Member

interface MemberRepo {
    fun save (member: Member): Member
    fun findById(id : Long) : Member?
    fun findByName(name: String) : Member?
    fun findAll() : List<Member>
}