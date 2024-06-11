package com.example.prac3_servlet.web.frontcontroller.v3

import com.example.prac3_servlet.web.frontcontroller.ModelView

interface ControllerV3 {

    fun process(paramMap: HashMap<String, String>):ModelView
}