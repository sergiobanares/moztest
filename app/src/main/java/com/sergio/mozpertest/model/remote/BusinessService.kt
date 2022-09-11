package com.sergio.mozpertest.model.remote

import com.sergio.mozpertest.model.local.EmployeeDao
import com.sergio.mozpertest.model.local.LocalEmployee
import com.sergio.mozpertest.model.remote.dto.BusinessDTO
import com.sergio.mozpertest.model.remote.dto.EmployeeDTO
import retrofit2.Response
import retrofit2.http.GET

interface BusinessService {

    @GET
    suspend fun getBusiness(): Response<BusinessDTO>
}
