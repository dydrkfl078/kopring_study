package prac2.core.common

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import java.util.UUID

@Component
@Scope("request")
class MyLogger {

    private lateinit var uuid: UUID
    private lateinit var requestUrl: String

    @PostConstruct
    fun init() {
        uuid = UUID.randomUUID()
        println(" [ $uuid ]  request scope bean create $this")
    }

    fun setRequestUrl(url: String){
        requestUrl = url
    }

    fun log(message: String) {
        println(" [ $uuid ] [ $requestUrl ] [ $message ]")
    }

    @PreDestroy
    fun close() {
        println(" [ $uuid ]  request scope bean close $this")
    }
}