package kopring.prac11_advanced.iterator.code

class StudentIterator(private val target: StudyRoom ) : Iterator<Student> {

    private var index = 0

    override fun hasNext(): Boolean {
        return index < target.getSize()
    }

    override fun next(): Student {
        return if (!hasNext()) throw NoSuchElementException() else target.getStudent(index++)
    }
}