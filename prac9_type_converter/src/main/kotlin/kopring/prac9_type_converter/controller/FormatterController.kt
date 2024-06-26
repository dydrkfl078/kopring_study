package kopring.prac9_type_converter.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import kopring.prac9_type_converter.type.FormatterForm
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import java.time.LocalDateTime

private val logger = KotlinLogging.logger {  }

@Controller
class FormatterController {

    @GetMapping("/formatter/edit")
    fun formatterForm(model: Model): String {
        val formatterForm = FormatterForm(10000.0, LocalDateTime.now())

        model.addAttribute("formatterForm", formatterForm)
        logger.info { model.getAttribute("formatterForm") }
        return "formatter/form"
    }

    @PostMapping("/formatter/edit")
    fun formatterEdit(@ModelAttribute formatterForm: FormatterForm, model: Model): String {
        model.addAttribute("formatterForm", formatterForm)
        logger.info { model.getAttribute("formatterForm") }
        return "formatter/view"
    }
}