package com.example.prac3_servlet.web.frontcontroller.v5.adapter

import com.example.prac3_servlet.web.frontcontroller.ModelView
import com.example.prac3_servlet.web.frontcontroller.v4.ControllerV4
import com.example.prac3_servlet.web.frontcontroller.v5.MyHandlerAdapter
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

class ControllerV4HandlerAdapter : MyHandlerAdapter{
    override fun supports(handler: Any): Boolean {
        return handler is ControllerV4
    }

    override fun handle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): ModelView {
        val controller = handler as ControllerV4
        val paramMap = createParamMap(request)
        val model = HashMap<String, Any>()

        val viewName = controller.process(paramMap,model)
        val mv = ModelView(viewName)
        mv.setModel(model)

        return mv
    }

    private fun createParamMap(request: HttpServletRequest): HashMap<String, String> {
        val tempMap = HashMap<String, String>()
        request.parameterNames.iterator().forEachRemaining { param -> tempMap[param] = request.getParameter(param) }
        return tempMap
    }
}