package kopring.prac11_advanced.state.code

import io.github.oshai.kotlinlogging.KotlinLogging
private val logger = KotlinLogging.logger {  }
class Number : Category{

    override fun write(content: String) {
        val result = content.replace(Regex("[^0-9]"), "")
        logger.info { "Number.write = $result" }
    }
}