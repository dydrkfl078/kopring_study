package com.example.prac3_servlet.basic.request

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet (name = "requestHeaderServlet" , urlPatterns = ["/request-header"])
class RequestHeaderServlet : HttpServlet() {

    override fun service(req: HttpServletRequest?, resp: HttpServletResponse?) {
        printHeaders(req!!)
    }
}

fun printHeaders(req: HttpServletRequest) {
    req.headerNames.asIterator().forEachRemaining { headerName ->
        println("HeaderName = $headerName")
    }
}