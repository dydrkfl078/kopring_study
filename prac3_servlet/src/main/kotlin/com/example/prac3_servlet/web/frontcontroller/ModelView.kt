package com.example.prac3_servlet.web.frontcontroller

class ModelView (private val viewName: String){

    private val model = HashMap<String, Any>()

    fun getViewName(): String{
        return viewName
    }

    fun getModel(): HashMap<String, Any>{
        return model
    }

    fun setModel(model : HashMap<String, Any>){
        this.model.clear()
        this.model.putAll(model)
    }
}