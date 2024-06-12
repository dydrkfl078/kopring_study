package com.example.prac3_servlet.web.frontcontroller.v5

import com.example.prac3_servlet.web.frontcontroller.ModelView
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.io.IOException

interface MyHandlerAdapter {

    fun supports(handler : Any): Boolean

    @Throws(ServletException::class, IOException::class)
    fun handle(request: HttpServletRequest, response : HttpServletResponse, handler : Any): ModelView
}