package kopring.prac11_advanced.trace

import java.util.*

class TraceId (val id : String = createUUID(), val level : Int = 0) {

    fun createNextId (): TraceId {
        return TraceId(id, level + 1)
    }

    fun createPrevId (): TraceId {
        return TraceId(id, level + -1)
    }

    fun isFirstLevel () : Boolean {
        return level == 0
    }


}

fun createUUID() : String {
    return UUID.randomUUID().toString().substring(0,8)
}