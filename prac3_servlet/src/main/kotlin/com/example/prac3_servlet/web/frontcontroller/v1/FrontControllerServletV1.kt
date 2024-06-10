package com.example.prac3_servlet.web.frontcontroller.v1

import com.example.prac3_servlet.web.frontcontroller.v1.controller.MemberFormControllerV1
import com.example.prac3_servlet.web.frontcontroller.v1.controller.MemberListControllerV1
import com.example.prac3_servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet (name = "frontControllerServletV1", urlPatterns = ["/front-controller/v1/*"])
class FrontControllerServletV1 : HttpServlet() {

    private val controllerMap = HashMap<String, ControllerV1>()

    init {
        controllerMap["/front-controller/v1/members/new-form"] = MemberFormControllerV1()
        controllerMap["/front-controller/v1/members/save"] = MemberSaveControllerV1()
        controllerMap["/front-controller/v1/members"] = MemberListControllerV1()
    }

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {

        val requestUrl = request.requestURI

        val controller = controllerMap[requestUrl]
        if (controller == null) response.status = HttpServletResponse.SC_NOT_FOUND
        else controller.process(request, response)
    }


}