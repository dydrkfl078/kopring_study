package practice_thymeleaf.kopring_prac1.service

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import practice_thymeleaf.kopring_prac1.domain.Member
import practice_thymeleaf.kopring_prac1.repository.JdbcTemplateMemberRepo
import practice_thymeleaf.kopring_prac1.repository.MemberRepo
import practice_thymeleaf.kopring_prac1.repository.MemoryMemberRepo

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest() {

    @Autowired
    private lateinit var memberRepo : MemberRepo

    @Autowired
    private lateinit var memberService : MemberService

    @Test
    fun 회원가입() {
        // given
        val memberA = Member("memberA")

        // when
        memberService.join(memberA)

        // then
        memberA.id shouldBe memberRepo.findById(memberA.id)?.id
    }

    @Test
    fun 모든맴버찾기() {
        // given
        val memberA = Member("memberA")
        val memberB = Member("memberB")
        val memberC = Member("memberC")

        memberService.join(memberA)
        memberService.join(memberB)
        memberService.join(memberC)

        // when
        val result = memberService.findMembers()

        // then
        result.size shouldBe 3

    }

    @Test
    fun ID로단일맴버찾기() {
        // given
        val memberA = Member("memberA")
        val memberB = Member("memberB")
        memberService.join(memberA)
        memberService.join(memberB)

        // when
        val result = memberService.findOne(memberA.id)

        // then
        result?.name shouldBe memberA.name

    }
}