package com.sergio.mozpertest.model

import com.sergio.mozpertest.model.dto.Business
import retrofit2.Response

interface BusinessRepository {

    suspend fun fetchBusiness(): Response<Business>
}