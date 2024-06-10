package com.example.prac3_servlet.web.frontcontroller.v1.controller

import com.example.prac3_servlet.domain.member.MemberRepo
import com.example.prac3_servlet.web.frontcontroller.v1.ControllerV1
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

class MemberListControllerV1 : ControllerV1 {

    val memberRepo = MemberRepo()

    override fun process(request: HttpServletRequest, response: HttpServletResponse) {
        val members = memberRepo.findAll()

        request.setAttribute("members",members)

        val viewPath = "/WEB-INF/views/members.jsp"
        val dispatcher = request.getRequestDispatcher(viewPath)
        dispatcher.forward(request, response)

    }
}