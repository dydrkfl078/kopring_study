package kopring.prac11_advanced.trace.callback

fun interface CallbackLogTrace <T> {
    fun call(): T
}