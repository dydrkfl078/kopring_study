package com.example.prac3_servlet.domain.member

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class MemberRepoTest {

    companion object {
        val memberRepo = MemberRepo()
    }

    @BeforeEach
    fun beforeEach(){
        memberRepo.clearMap()
    }

    @Test
    fun save(){
        val member = Member("memberA",20)

        memberRepo.save(member)

        memberRepo.findById(0L)!!.id shouldBe member.id
    }

    @Test
    fun findAll(){
        val memberA = Member("memberA",19)
        val memberB = Member("memberB",30)

        memberRepo.save(memberA)
        memberRepo.save(memberB)

        memberRepo.findAll().size shouldBe 2
    }
}