package com.example.prac3_servlet.web.servletmvc

import com.example.prac3_servlet.domain.member.MemberRepo
import jakarta.servlet.ServletException
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.io.IOException

@WebServlet (name = "mvcMembersServlet", urlPatterns = ["/servlet-mvc/members"])
class MvcMembersServlet : HttpServlet() {

    private val memberRepo = MemberRepo()

    @Throws(ServletException::class, IOException::class)
    override fun service(req: HttpServletRequest, resp: HttpServletResponse) {

        val members = memberRepo.findAll()

        req.setAttribute("members",members)

        val viewPath = "/WEB-INF/views/members.jsp"
        val dispatcher = req.getRequestDispatcher(viewPath)
        dispatcher.forward(req, resp)

    }
}