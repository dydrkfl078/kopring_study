package com.example.prac3_servlet.web.frontcontroller.v3.controller

import com.example.prac3_servlet.domain.member.Member
import com.example.prac3_servlet.domain.member.MemberRepo
import com.example.prac3_servlet.web.frontcontroller.ModelView
import com.example.prac3_servlet.web.frontcontroller.v3.ControllerV3

class MemberSaveControllerV3: ControllerV3 {

    private val memberRepo = MemberRepo()

    override fun process(paramMap: HashMap<String, String>): ModelView {
        val username = paramMap["username"]!!
        val age = paramMap["age"]!!.toInt()

        val member = Member(username, age)
        memberRepo.save(member)

        val mv = ModelView("save-result")
        mv.getModel()["member"] = member

        return mv
    }
}