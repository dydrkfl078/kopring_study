package kopring.prac11_advanced.singleton

class CompanionMyBookmark private constructor(){

    companion object {
        val instance: CompanionMyBookmark by lazy { CompanionMyBookmark() }
    }

    private val myBookmarkMap = LinkedHashMap<String, String>()

    fun addBookmark(url: String, desc: String) {
        myBookmarkMap[url] = desc
    }

    fun removeBookmark(url: String) {
        myBookmarkMap.remove(url)
    }

    fun readAllBookmark(): Map<String, String> {
        return myBookmarkMap
    }

    fun clearBookmark() {
        myBookmarkMap.clear()
    }
}