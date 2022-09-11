package com.sergio.mozpertest.model.remote

import com.sergio.mozpertest.model.remote.dto.BusinessDTO
import retrofit2.Response
import retrofit2.http.GET

interface BusinessService {

    @GET
    suspend fun getBusiness(): Response<BusinessDTO>
}
