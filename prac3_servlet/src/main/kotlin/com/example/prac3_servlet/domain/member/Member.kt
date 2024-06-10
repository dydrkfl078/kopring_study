package com.example.prac3_servlet.domain.member

data class Member(private val name: String, private val age: Int){

    var id : Long = -1L

    fun getIdNum() : Long = id

    fun getName(): String{
        return name
    }

    fun getAge(): Int{
        return age
    }
}