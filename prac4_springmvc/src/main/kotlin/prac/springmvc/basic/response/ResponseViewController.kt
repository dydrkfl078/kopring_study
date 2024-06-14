package prac.springmvc.basic.response

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class ResponseViewController {

    @RequestMapping("/response-view-v1")
    fun responseViewV1(): ModelAndView {
        val mv = ModelAndView("response/hello")
            .addObject("data","Response View v1")

        return mv
    }

    @RequestMapping("/response-view-v2")
    fun responseViewV2(model: Model): String {
        model.addAttribute("data","Response View v2")
        return "response/hello"
    }
}