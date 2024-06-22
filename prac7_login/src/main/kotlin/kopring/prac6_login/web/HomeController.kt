package kopring.prac6_login.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/home")
class HomeController {

    @GetMapping
    fun home():String {
        return "home"
    }
}