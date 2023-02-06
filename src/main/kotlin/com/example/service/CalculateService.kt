package com.example.service

import com.example.data.classes.CalculateResponse

class CalculateService {

    fun calculate(numb1 : Int, numb2: Int, operator: String) : CalculateResponse {
        // Checking what kind of calculation we want to do
        val sum : Int = when(operator.toString()){
            "+" -> numb1 + numb2
            "-" -> numb1 - numb2
            "*" -> numb1 * numb2
            "/" -> numb1 / numb2
            else -> 0
        }
        // Checking if it's advanced calculation
        val isAdvanced = isAdvancedMaths(operator)
        return CalculateResponse(sum, isAdvanced)
    }
    private fun isAdvancedMaths(operator: String) : Boolean {
        return operator == "*" || operator == "/"
    }
}