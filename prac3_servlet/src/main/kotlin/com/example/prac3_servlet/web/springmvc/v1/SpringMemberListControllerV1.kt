package com.example.prac3_servlet.web.springmvc.v1

import com.example.prac3_servlet.domain.member.MemberRepo
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class SpringMemberListControllerV1 {

    private val memberRepo = MemberRepo()

    @RequestMapping("/springmvc/v1/members")
    fun process(request: HttpServletRequest, response : HttpServletResponse): ModelAndView{
        val members = memberRepo.findAll()
        val mv = ModelAndView("members")
            mv.addObject("members",members)

        return mv
    }
}