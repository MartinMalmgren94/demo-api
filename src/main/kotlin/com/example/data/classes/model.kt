package com.example.data.classes

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern


data class CalculateResponse(val sum : Int, val isAdvanced : Boolean)
data class InfoResponse(val info : String)

class CalculateBody {
    @field:NotBlank @Pattern(regexp = "^([+\\-*{\\/}])\$") var operator: String = ""
    @field:NotNull var numb1 : Int = 0
    @field:NotNull var numb2 : Int = 0
}
