package com.example.prac3_servlet.web.frontcontroller.v2.controller

import com.example.prac3_servlet.domain.member.Member
import com.example.prac3_servlet.domain.member.MemberRepo
import com.example.prac3_servlet.web.frontcontroller.MyView
import com.example.prac3_servlet.web.frontcontroller.v2.ControllerV2
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

class MemberSaveControllerV2 : ControllerV2{

    private val memberRepo = MemberRepo()

    override fun process(request: HttpServletRequest, response: HttpServletResponse): MyView {
        val username = request.getParameter("username")
        val age = request.getParameter("age").toInt()

        val member = Member(username, age)
        memberRepo.save(member)

        request.setAttribute("member", member)

        return MyView("/WEB-INF/views/save-result.jsp")
    }
}