package com.example.prac3_servlet.web.springmvc.v1

import com.example.prac3_servlet.domain.member.Member
import com.example.prac3_servlet.domain.member.MemberRepo
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class SpringMemberSaveControllerV2 {

    private val memberRepo = MemberRepo()

    @RequestMapping("/springmvc/v1/members/save")
    fun process(request: HttpServletRequest, response: HttpServletResponse): ModelAndView {
        val username = request.getParameter("username")
        val age = request.getParameter("age").toInt()
        val member = Member(username,age)

        memberRepo.save(member)

        val mv = ModelAndView("save-result")
        mv.addObject("member", member)
        return mv
    }
}