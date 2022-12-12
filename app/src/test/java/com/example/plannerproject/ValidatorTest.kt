package com.example.plannerproject


import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ValidatorTest{

    @Test
    fun whenInputIsValid() {
        val task = "Some random task"
        val desc = "Some random desc"
        val result = Validator.validateInput(task, desc)
        assertThat(result).isEqualTo(true)
    }
}