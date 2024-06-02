package practice_thymeleaf.kopring_prac1.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import practice_thymeleaf.kopring_prac1.domain.Member
import practice_thymeleaf.kopring_prac1.repository.MemberRepo
import practice_thymeleaf.kopring_prac1.repository.MemoryMemberRepo

@Transactional
class MemberService(private val memoryMemberRepo: MemberRepo) {

    fun join(member : Member): Long {
        memoryMemberRepo.findByName(member.name)?.let {
            throw IllegalStateException("이미 존재하는 회원입니다.")
        } ?: memoryMemberRepo.save(member)

        return member.id
    }

    fun findMembers(): List<Member> {
        return memoryMemberRepo.findAll()
    }

    fun findOne(id: Long): Member? {
        return memoryMemberRepo.findById(id)
    }
}