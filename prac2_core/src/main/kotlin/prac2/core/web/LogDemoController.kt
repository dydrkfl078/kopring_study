package prac2.core.web

import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.ObjectProvider
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import prac2.core.common.MyLogger

@Controller
class LogDemoController(private val myLogger: MyLogger, private val logDemoService: LogDemoService) {

    @RequestMapping("log")
    @ResponseBody
    fun log(request: HttpServletRequest): String {
        myLogger.setRequestUrl(request.requestURL.toString())

        myLogger.log("Controller OK")
        logDemoService.logic("TEST ID 1")
        return "OK"
    }
}