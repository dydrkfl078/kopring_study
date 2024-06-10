package com.example.prac3_servlet.web.servletmvc

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet (name = "mvcMemberFormServlet", urlPatterns = ["/servlet-mvc/members/new-form"])
class MvcMemberFormServlet : HttpServlet() {

    override fun service(req: HttpServletRequest, resp: HttpServletResponse) {

        println("test")

        val viewPath = "/WEB-INF/views/new-form.jsp"
        val dispatcher = req.getRequestDispatcher(viewPath)

        dispatcher.forward(req, resp)

    }
}