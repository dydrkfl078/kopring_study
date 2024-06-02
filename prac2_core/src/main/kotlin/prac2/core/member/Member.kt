package prac2.core.member

class Member (id: Long, name: String, grade: Grade){
    private val id : Long = id
    private val name = name
    private val grade = grade

    fun getId() : Long { return id }
    fun getName() = name
    fun getGrade() = grade
}