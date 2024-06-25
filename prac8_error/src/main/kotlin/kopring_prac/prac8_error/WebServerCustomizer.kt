package kopring_prac.prac8_error

import org.springframework.boot.web.server.ConfigurableWebServerFactory
import org.springframework.boot.web.server.ErrorPage
import org.springframework.boot.web.server.WebServerFactoryCustomizer
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class WebServerCustomizer: WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    override fun customize(factory: ConfigurableWebServerFactory?) {

        val error404 = ErrorPage(HttpStatus.NOT_FOUND, "/error-page/404")
        val error500 = ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error-page/500")
        val errorPageEx = ErrorPage(RuntimeException::class.java, "/error-page/500")

        factory!!.addErrorPages(error404,error500,errorPageEx)
    }
}