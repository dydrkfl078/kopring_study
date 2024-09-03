package kopring.prac11_advanced.proxy.pure.code

class ProxyPatternClient (private val subject: Subject){

    fun execute() {
        subject.operation()
    }
}