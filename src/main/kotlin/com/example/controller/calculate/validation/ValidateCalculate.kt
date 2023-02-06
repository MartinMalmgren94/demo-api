package com.example.controller.calculate.validation

data class ValidateCalculate(val numb1: Int, val numb2: Int, val operator : String){
    init {
        if(this.numb1 === null) throw Exception("Numb1 need to have a value.")
        if(this.numb2 === null) throw Exception("Numb2 need to have a value.")
        if(!this.operator.matches(Regex("^([+\\-*{\\/}])$"))) throw Exception("operator can only be +, -, /, *")
    }
}
