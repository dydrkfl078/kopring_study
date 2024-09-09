package kopring.prac11_advanced.iterator

import kopring.prac11_advanced.iterator.code.StudyRoom
import org.junit.jupiter.api.Test
import java.sql.Date
import java.time.LocalDate
import io.github.oshai.kotlinlogging.KotlinLogging
import io.kotest.assertions.print.print
import kopring.prac11_advanced.iterator.code.Student

private val logger = KotlinLogging.logger {  }
class IteratorTest {

    private val room1 = StudyRoom()

    @Test
    fun iteratorTest(){
        room1.addStudent("test1",7, Date.valueOf(LocalDate.of(2011,1,1)))
        room1.addStudent("test2",18, Date.valueOf(LocalDate.of(2000,1,1)))
        room1.addStudent("test3",14, Date.valueOf(LocalDate.of(2004,1,1)))

        // 객체, map, list 모두 일관된 접근 방식을 제공하며, 상태
        val iteratorObject = room1
        iteratorObject.iterator().printIterator()

        val iteratorMap = mapOf(1 to "test1", 2 to "test2", 3 to "test3")
        iteratorMap.iterator().printIterator()

        val iteratorList = listOf("test1","test2","test3")
        iteratorList.iterator().printIterator()

        // 람다형식으로 만들기
        iteratorList.iterator().returnIterator { it ->
            logger.info { it }
        }
    }
}

fun <T> Iterator<T>.printIterator (){
    var itr = this
    while (itr.hasNext()) {
        when (val stu = itr.next()) {
            is Student -> { logger.info { "Student name : ${stu.name}, age : ${stu.age}, birth : ${stu.birth}" }}
            is Map.Entry<*,* > -> { logger.info { "key - ${stu.key}, value - ${stu.value}" }}
            else -> { logger.info { "value : $stu" }}
        }
    }
}

fun <T> Iterator<T>.returnIterator (action: (T) -> Unit): Unit {
    var itr = this
    while (itr.hasNext()) {
        val element = itr.next()
        action(element)
    }
}
