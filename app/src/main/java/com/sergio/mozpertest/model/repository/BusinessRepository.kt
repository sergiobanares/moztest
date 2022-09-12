package com.sergio.mozpertest.model.repository

import com.sergio.mozpertest.model.local.LocalEmployee

interface BusinessRepository {

    suspend fun getEmployees(): List<LocalEmployee>
    //suspend fun getEmployeeDetail(employeeUID: String): LocalEmployee
}