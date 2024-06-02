package practice_thymeleaf.kopring_prac1.repo


import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import practice_thymeleaf.kopring_prac1.domain.Member
import practice_thymeleaf.kopring_prac1.repository.MemoryMemberRepo

internal class MemoryMemberRepoTest {

    companion object {
        val memoryMemberRepo = MemoryMemberRepo()
    }
    @BeforeEach
    fun beforeEach() {
        memoryMemberRepo.clear()
    }

    @Test
    fun save() {

        val member1 = Member("member1")
        val member2 = Member("member2")

        memoryMemberRepo.save(member1)
        memoryMemberRepo.save(member2)

        val member1Result = memoryMemberRepo.findById(member1.id)?.name
        val member2Result = memoryMemberRepo.findById(member2.id)?.name

        member1Result shouldBe "member1"
        member2Result shouldBe "member2"
    }

    @Test
    fun findByName () {

        val member3 = Member("member3")
        val member4 = Member("member4")

        memoryMemberRepo.save(member3)
        memoryMemberRepo.save(member4)

        val member3Result = memoryMemberRepo.findByName("member3")?.id
        val member4Result = memoryMemberRepo.findByName("member4")?.id
        val member5Result = memoryMemberRepo.findByName("member5")?.id

        member3Result shouldBe 1
        member4Result shouldBe 2
        member5Result shouldBe null
    }

    @Test
    fun findAll() {

        val member3 = Member("member3")
        val member4 = Member("member4")

        memoryMemberRepo.save(member3)
        memoryMemberRepo.save(member4)

        val memberList = memoryMemberRepo.findAll()

        memberList.size shouldBe 2
    }
}
