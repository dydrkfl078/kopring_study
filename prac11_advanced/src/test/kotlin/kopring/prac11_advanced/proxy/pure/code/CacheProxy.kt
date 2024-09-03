package kopring.prac11_advanced.proxy.pure.code

import io.github.oshai.kotlinlogging.KotlinLogging


private val logger = KotlinLogging.logger {  }
class CacheProxy(private val target : Subject) : Subject {

    private lateinit var cacheValue: String

    override fun operation(): String {
        logger.info { "프록시 호출" }

        if (::cacheValue.isInitialized) {
            return cacheValue
        }

        cacheValue = target.operation()

        return cacheValue
    }
}