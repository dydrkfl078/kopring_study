package kopring.thymeleaf.basic

import jakarta.servlet.http.HttpSession
import org.springframework.stereotype.Component
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/basic")
class BasicController {

    @GetMapping("text-basic")
    fun textBasic(model : Model) :String {
        model.addAttribute("data","Hello Thymeleaf!")
        model.addAttribute("data2","<b>is bold?</b>")
        return "/basic/text-basic"
    }

    @GetMapping("variable")
    fun variable(model : Model) :String {
        val testList = listOf(1,2,3,4,5)
        model.addAttribute("list",testList)
        return "/basic/variable"
    }

    @GetMapping("basic-objects")
    fun basicObjects(session : HttpSession) :String {
        session.setAttribute("sessionData","Hello Session!")

        return "/basic/basic-objects"
    }

    @GetMapping("link")
    fun link(): String{
        return "/basic/link"
    }

    @GetMapping("operation")
    fun operation(model : Model):String{
        model.addAttribute("data","Spring")
        model.addAttribute("noData",null)
        return "basic/operation"
    }

    @GetMapping("attribute")
    fun attribute():String{
        return "basic/attribute"
    }

    @GetMapping("each")
    fun each(model : Model): String {
        val testList = listOf("cavempt","Gallery Dept","kith","nooah","Miyake")
        model.addAttribute("data",testList)
        return "basic/each"
    }

    @GetMapping("comments")
    fun comment(model : Model):String {
        model.addAttribute("data","Comment Test!")
        return "basic/comments"
    }

    @GetMapping("block")
    fun block(model : Model): String {
        val testList = listOf("cavempt","Gallery Dept","kith","nooah","Miyake")
        model.addAttribute("data",testList)
        return "basic/block"
    }
}

@Component
class TestSpringBean(){
    fun hello(string: String = ""): String {
        return "HelloSpringBean${string}"
    }
}