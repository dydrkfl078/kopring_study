package com.example.prac3_servlet.web.springmvc.old

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.HttpRequestHandler

@Component ("/springmvc/request-handler")
class OldHttpRequestHandler : HttpRequestHandler {

    override fun handleRequest(request: HttpServletRequest, response: HttpServletResponse) {
        println("OldHttpRequestHandler.handleRequest")
    }
}