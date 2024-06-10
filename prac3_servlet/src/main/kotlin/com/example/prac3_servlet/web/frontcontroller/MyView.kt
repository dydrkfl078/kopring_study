package com.example.prac3_servlet.web.frontcontroller

import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.io.IOException
import kotlin.jvm.Throws

class MyView (private val viewPath: String) {

    @Throws(ServletException::class, IOException::class)
    fun render(request : HttpServletRequest, response: HttpServletResponse) {
        val dispatcher = request.getRequestDispatcher(viewPath)
        dispatcher.forward(request, response)
    }
}