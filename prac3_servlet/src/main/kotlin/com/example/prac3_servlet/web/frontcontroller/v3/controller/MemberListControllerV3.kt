package com.example.prac3_servlet.web.frontcontroller.v3.controller

import com.example.prac3_servlet.domain.member.MemberRepo
import com.example.prac3_servlet.web.frontcontroller.ModelView
import com.example.prac3_servlet.web.frontcontroller.v3.ControllerV3

class MemberListControllerV3 : ControllerV3 {

    private val memberRepo = MemberRepo()

    override fun process(paramMap: HashMap<String, String>): ModelView {

        val members = memberRepo.findAll()

        val mv = ModelView("members")
        mv.getModel()["members"] = members
        return mv
    }
}