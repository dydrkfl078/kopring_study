package com.example.prac3_servlet.web.frontcontroller.v5.adapter

import com.example.prac3_servlet.web.frontcontroller.ModelView
import com.example.prac3_servlet.web.frontcontroller.v3.ControllerV3
import com.example.prac3_servlet.web.frontcontroller.v5.MyHandlerAdapter
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

class ControllerV3HandlerAdapter : MyHandlerAdapter{
    override fun supports(handler: Any): Boolean {
        return handler is ControllerV3
    }

    override fun handle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): ModelView {
        val controller = handler as ControllerV3
        val paramMap = createParamMap(request)
        val mv = controller.process(paramMap)

        return mv
    }

    private fun createParamMap(request: HttpServletRequest): HashMap<String, String> {
        val tempParamMap = HashMap<String,String>()
        request.parameterNames.iterator().forEachRemaining { paramName -> tempParamMap[paramName] = request.getParameter(paramName) }
        return tempParamMap
    }
}