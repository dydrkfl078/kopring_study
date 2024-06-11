package com.example.prac3_servlet.web.frontcontroller.v4.controller

import com.example.prac3_servlet.domain.member.Member
import com.example.prac3_servlet.domain.member.MemberRepo
import com.example.prac3_servlet.web.frontcontroller.v4.ControllerV4

class MemberSaveControllerV4 : ControllerV4 {
    
    private val memberRepo = MemberRepo()
    
    override fun process(paramMap: HashMap<String, String>, model: HashMap<String, Any>): String {
        val username = paramMap["username"]!!
        val age = paramMap["age"]!!.toInt()
        val member = Member(username, age)
        memberRepo.save(member)

        model["member"] = member
        
        return "save-result"
    }
}