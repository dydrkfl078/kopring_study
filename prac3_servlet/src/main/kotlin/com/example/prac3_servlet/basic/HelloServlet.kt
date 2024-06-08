package com.example.prac3_servlet.basic

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet (name = "helloServlet", urlPatterns = ["/hello"])
class HelloServlet : HttpServlet() {

    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        println("HelloServlet.service")
        println("req = ${req}")
        println("resp = ${resp}")

        val userName = req!!.getParameter("username")
        println(userName)

        resp!!.apply {
            contentType = "text/plain"
            characterEncoding = "utf-8"
            writer.write("Hello $userName")
        }

//        super.service(req, resp)
    }
}