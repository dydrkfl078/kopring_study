package kopring.prac11_advanced.v1_decorator

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Repository

private val logger = KotlinLogging.logger {  }

class DecoOrderRepositoryImplV1 : DecoOrderRepositoryV1 {

    override fun save (itemName: String) {
        logger.info { "Repository - save ... " }

        if (itemName.contains(DecoOrderRepositoryV1.ERROR_NAME)) throw RuntimeException()

        Thread.sleep(1000L)
    }
}