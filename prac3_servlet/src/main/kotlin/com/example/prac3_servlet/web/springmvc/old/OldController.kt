package com.example.prac3_servlet.web.springmvc.old

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.Controller

@Component (value = "/springmvc/old-controller")
class OldController : Controller {

    override fun handleRequest(request: HttpServletRequest, response: HttpServletResponse): ModelAndView? {
        println("OldController.handleRequest")
        return ModelAndView("new-form")
    }
}