package com.example.prac3_servlet.web.frontcontroller.v3.controller

import com.example.prac3_servlet.web.frontcontroller.ModelView
import com.example.prac3_servlet.web.frontcontroller.v3.ControllerV3

class MemberFormControllerV3 : ControllerV3 {
    override fun process(paramMap: HashMap<String, String>): ModelView {
        return ModelView("new-form")
    }
}