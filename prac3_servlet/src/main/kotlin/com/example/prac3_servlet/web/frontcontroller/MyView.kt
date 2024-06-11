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

    @Throws(ServletException::class, IOException::class)
    fun render(model : HashMap<String,Any>, request : HttpServletRequest, response: HttpServletResponse) {
        model.forEach { (key, value) -> request.setAttribute(key, value) }
        val dispatcher = request.getRequestDispatcher(viewPath)
        dispatcher.forward(request, response)
    }
}