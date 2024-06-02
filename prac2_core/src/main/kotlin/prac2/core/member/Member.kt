package prac2.core.member

class Member (id: Long, name: String, grade: Grade){
    private val id = id
    private val name = name
    private val grade = grade

    fun getId() = id
    fun getName() = name
    fun getGrade() = grade
}