package com.example.prac3_servlet.web.springmvc.v2

import com.example.prac3_servlet.domain.member.Member
import com.example.prac3_servlet.domain.member.MemberRepo
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/springmvc/v2/members")
class SpringMemberControllerV2 {

    private val memberRepo = MemberRepo()

    @GetMapping("/new-form")
    fun newForm(): ModelAndView{
        return ModelAndView("new-form")
    }

    @PostMapping("/save")
    fun save(request: HttpServletRequest): ModelAndView {
        val username = request.getParameter("username")!!
        val age = request.getParameter("age")!!.toInt()
        val member= Member(username,age)

        memberRepo.save(member)

        val mv = ModelAndView("save-result")
            mv.addObject("member", member)

        return mv
    }

    @GetMapping
    fun findAll(): ModelAndView {
        val members = memberRepo.findAll()

        val mv = ModelAndView("members")
        mv.addObject("members", members)

        return mv
    }
}