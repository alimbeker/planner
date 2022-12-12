package com.example.plannerproject

object Validator {
    fun validateInput(task: String, desc: String): Boolean {
       return !(task.isEmpty() || desc.isEmpty())
    }
}