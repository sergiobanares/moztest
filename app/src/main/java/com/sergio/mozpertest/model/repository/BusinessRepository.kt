package com.sergio.mozpertest.model.repository

import com.sergio.mozpertest.model.remote.dto.BusinessDTO
import retrofit2.Response

interface BusinessRepository {

    suspend fun fetchBusiness(): Response<BusinessDTO>

}
