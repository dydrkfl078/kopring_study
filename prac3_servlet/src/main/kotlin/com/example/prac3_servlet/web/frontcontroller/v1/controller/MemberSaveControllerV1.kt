package com.example.prac3_servlet.web.frontcontroller.v1.controller

import com.example.prac3_servlet.domain.member.Member
import com.example.prac3_servlet.domain.member.MemberRepo
import com.example.prac3_servlet.web.frontcontroller.v1.ControllerV1
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

class MemberSaveControllerV1 : ControllerV1{

    private val memberRepo = MemberRepo()

    override fun process(request: HttpServletRequest, response: HttpServletResponse) {

        val username = request.getParameter("username")
        val age = request.getParameter("age").toInt()

        val member = Member(username, age)
        memberRepo.save(member)

        request.setAttribute("member", member)

        val viewPath = "/WEB-INF/views/save-result.jsp"
        val dispatcher = request.getRequestDispatcher(viewPath)
        dispatcher.forward(request, response)
    }
}