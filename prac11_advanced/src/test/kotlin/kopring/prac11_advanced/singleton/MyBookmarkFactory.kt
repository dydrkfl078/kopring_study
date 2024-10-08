package kopring.prac11_advanced.singleton

object MyBookmarkFactory {
    val myBookmark : MyBookmark by lazy { MyBookmark() }
}

class MyBookmark {

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

