package practice_thymeleaf.kopring_prac1.service

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import practice_thymeleaf.kopring_prac1.domain.Member
import practice_thymeleaf.kopring_prac1.repository.MemoryMemberRepo

class MemberServiceTest {

    companion object {
        val memoryMemberRepo = MemoryMemberRepo()
    }


    @BeforeEach
    fun beforeEach() {
        memoryMemberRepo.clear()
    }

    @Test
    fun 회원가입() {
        // given
        val service = MemberService(memoryMemberRepo)
        val memberA = Member("memberA")

        // when
        service.join(memberA)

        // then
        memberA.id shouldBe memoryMemberRepo.findById(memberA.id)?.id
    }

    @Test
    fun 모든맴버찾기() {
        // given
        val service = MemberService(memoryMemberRepo)
        val memberA = Member("memberA")
        val memberB = Member("memberB")
        val memberC = Member("memberC")

        service.join(memberA)
        service.join(memberB)
        service.join(memberC)

        // when
        val result = service.findMembers()

        // then
        result.size shouldBe 3

    }

    @Test
    fun ID로단일맴버찾기() {
        // given
        val service = MemberService(memoryMemberRepo)
        val memberA = Member("memberA")
        val memberB = Member("memberB")
        service.join(memberA)
        service.join(memberB)

        // when
        val result = service.findOne(memberA.id)

        // then
        result?.name shouldBe memberA.name

    }
}