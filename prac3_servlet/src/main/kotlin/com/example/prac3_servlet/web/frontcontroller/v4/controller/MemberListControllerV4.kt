package com.example.prac3_servlet.web.frontcontroller.v4.controller

import com.example.prac3_servlet.domain.member.MemberRepo
import com.example.prac3_servlet.web.frontcontroller.v4.ControllerV4

class MemberListControllerV4 : ControllerV4{

    val memberRepo = MemberRepo()

    override fun process(paramMap: HashMap<String, String>, model: HashMap<String, Any>): String {
        val members = memberRepo.findAll()

        model["members"] = members

        return "members"
    }
}