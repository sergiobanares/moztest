package com.sergio.mozpertest.model

import com.sergio.mozpertest.model.dto.Business
import retrofit2.Response
import retrofit2.http.GET

interface BusinessService {
    @GET
    suspend fun getBusiness(): Response<Business>
}