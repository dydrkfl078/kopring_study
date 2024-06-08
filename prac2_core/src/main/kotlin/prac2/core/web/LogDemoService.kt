package prac2.core.web

import org.springframework.beans.factory.ObjectProvider
import org.springframework.stereotype.Service
import prac2.core.common.MyLogger

@Service
class LogDemoService(private val myLogger: MyLogger) {

    fun logic(id: String){
        myLogger.log("Service ID = $id")
    }
}
