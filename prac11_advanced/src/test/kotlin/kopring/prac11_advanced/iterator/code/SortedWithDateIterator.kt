package kopring.prac11_advanced.iterator.code

import org.springframework.util.CompositeIterator

class SortedWithDateIterator(private val target: List<Student>): CompositeIterator<Student>() {

    private val sortedWithDateIterator : Iterator<Student> = target.sortedBy { it.age }.also { println(it) }.iterator()

    override fun hasNext(): Boolean {
        return sortedWithDateIterator.hasNext()
    }

    override fun next(): Student {
        return sortedWithDateIterator.next()
    }
}