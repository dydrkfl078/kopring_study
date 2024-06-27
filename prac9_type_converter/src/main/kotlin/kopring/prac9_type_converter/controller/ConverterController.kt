package kopring.prac9_type_converter.controller

import kopring.prac9_type_converter.type.IpPort
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class ConverterController {

    @GetMapping("/converter-view")
    fun converterView(model : Model): String {
        model.addAttribute("number",10000)
        model.addAttribute("ipPort", IpPort("171,1,1,1",8080))
        return "converter-view"
    }

    @GetMapping("/converter-edit")
    fun converterForm(model: Model): String {
        model.addAttribute("form", Form(IpPort("171,1,1,1",8080)))
        return "converter-edit"
    }

    @PostMapping("/converter-edit")
    fun converterEdit(@ModelAttribute form : Form, model : Model): String {
        model.addAttribute("ipPort", form.ipPort)
        return "converter-view"
    }
}

class Form(val ipPort: IpPort){

}