package com.example.prac3_servlet.web.frontcontroller.v2.controller

import com.example.prac3_servlet.domain.member.MemberRepo
import com.example.prac3_servlet.web.frontcontroller.MyView
import com.example.prac3_servlet.web.frontcontroller.v2.ControllerV2
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

class MemberListControllerV2 : ControllerV2{

    private val memberRepo = MemberRepo()

    override fun process(request: HttpServletRequest, response: HttpServletResponse): MyView {

        val members = memberRepo.findAll()

        request.setAttribute("members",members)

        return MyView("/WEB-INF/views/members.jsp")

    }
}