package com.sergio.mozpertest.model.remote.dto

import com.sergio.mozpertest.model.local.LocalEmployee

// All this properties should be nullable
data class EmployeeDTO(
    val description: String,
    val firstName: String,
    val id: Int,
    val image: String,
    val lastName: String,
    val rating: Double
){
    fun toLocalEmployee() = LocalEmployee(
        employeeUID = id
    )
}
