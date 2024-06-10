package com.example.prac3_servlet.web.frontcontroller.v1

import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.io.IOException


interface ControllerV1 {

    @Throws(ServletException::class, IOException::class)
    fun process(request: HttpServletRequest, response: HttpServletResponse)
}