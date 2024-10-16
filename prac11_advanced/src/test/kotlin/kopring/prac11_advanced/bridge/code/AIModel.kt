package kopring.prac11_advanced.bridge.code

abstract class AIModel(protected val aiMethod: AIMethod) {
    abstract fun apiRequest(query: String)
}