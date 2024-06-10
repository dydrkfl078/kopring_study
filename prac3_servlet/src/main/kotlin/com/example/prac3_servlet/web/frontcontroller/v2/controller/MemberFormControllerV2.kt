package com.example.prac3_servlet.web.frontcontroller.v2.controller

import com.example.prac3_servlet.web.frontcontroller.MyView
import com.example.prac3_servlet.web.frontcontroller.v2.ControllerV2
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

class MemberFormControllerV2: ControllerV2 {

    override fun process(request: HttpServletRequest, response: HttpServletResponse): MyView {
        return MyView("/WEB-INF/views/new-form.jsp")
    }
}