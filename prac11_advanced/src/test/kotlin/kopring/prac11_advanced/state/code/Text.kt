package kopring.prac11_advanced.state.code

import io.github.oshai.kotlinlogging.KotlinLogging
private val logger = KotlinLogging.logger {  }

class Text: Category {
    override fun write(content: String) {
        logger.info { "Text = $content" }
    }
}