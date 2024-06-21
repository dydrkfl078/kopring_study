package kopringprac.registration.web.validator

import io.github.oshai.kotlinlogging.KotlinLogging
import kopringprac.registration.web.validator.form.ItemSaveForm
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

private val logger = KotlinLogging.logger {  }

@RestController
@RequestMapping("/validation/api/items")
class ValidationController {

    @PostMapping("add")
    fun addItem(@RequestBody @Validated itemSaveForm: ItemSaveForm, bindingResult: BindingResult ) : Any {

        logger.info { "API 컨트롤러 호출" }

        if (bindingResult.hasErrors()) {
            logger.info { "검증 오류 발생 = $bindingResult" }
            return bindingResult.allErrors
        }

        logger.info { "성공 로직 실행" }
        return itemSaveForm
    }
}