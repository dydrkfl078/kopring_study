package prac2.core.web

import org.springframework.beans.factory.ObjectProvider
import org.springframework.stereotype.Service
import prac2.core.common.MyLogger

@Service
class LogDemoService(private val myLoggerProvider: ObjectProvider<MyLogger>) {

    fun logic(id: String){
        val myLogger = myLoggerProvider.getObject()
        myLogger.log("Service ID = $id")
    }
}
