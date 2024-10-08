package kopring.prac11_advanced.singleton

import io.github.oshai.kotlinlogging.KotlinLogging
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

private val logger = KotlinLogging.logger {  }
class SingletonTest {

    @BeforeEach
    fun bookmarkTest(){
        addBookmark("naver.com","네이버")
        addBookmark("google.com","구글")
        addBookmark("daum.com","다음")
        addBookmark("facebook.com","페이스북")
        addBookmark("amazon.com","아마존")
    }

    @Test
    fun readAllBookmarkTest() {
        // given
        val myBookmark = MyBookmarkFactory.myBookmark

        // when
        val result = myBookmark.readAllBookmark()

        // then
        logger.info { result }
        result.size shouldBe 5
    }

    private fun addBookmark(url: String, desc: String){
        val myBookmark = MyBookmarkFactory.myBookmark

        myBookmark.addBookmark(url, desc)
    }

    /*
    *  Companion object
    */

    private fun addBookmarkCompanion(url: String, desc: String) {
        val myBookmark = CompanionMyBookmark.instance

        myBookmark.addBookmark(url, desc)
    }
}