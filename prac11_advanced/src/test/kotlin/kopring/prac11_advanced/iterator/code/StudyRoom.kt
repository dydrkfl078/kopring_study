package kopring.prac11_advanced.iterator.code

import java.util.Date

class StudyRoom : Iterable<Student> {

    private val students = mutableListOf<Student>()
    private var size = 0

    fun addStudent(name: String, age:Int, birth: Date){
        students.add(Student(name,age,birth))
        size++
    }

    fun getSize(): Int {
        return size
    }

    fun getStudent(index: Int): Student {
        return students[index]
    }

    override fun iterator(): Iterator<Student> {
        return StudentIterator(this)
    }
}