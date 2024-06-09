package com.example.prac3_servlet.basic.request

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.boot.web.servlet.ServletComponentScan

@WebServlet (name = "requestParamServlet", urlPatterns = ["/request-param"])
class RequestParamServlet : HttpServlet() {

    override fun service(req: HttpServletRequest?, resp: HttpServletResponse?) {

        req?.let { request -> request.parameterNames.iterator().forEachRemaining { println(" $it : ${req.getParameter(it)}") } }
    }
}