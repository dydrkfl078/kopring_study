package prac2.core.lifecycle

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy

class TestNetworkClient  {

    private lateinit var url : String

    fun setUrl (url: String){
        this.url = url
    }

    @PostConstruct
    private fun connect() {
        println("Connect Successful, Url - $url")
        call("초기화 연결 메시지")
    }

    @PreDestroy
    private fun disconnect() {
        println("Disconnect Url - $url")
    }

    private fun call(message : String){
        println("Call Url - $url, message = $message")
    }
}