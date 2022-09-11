package com.sergio.mozpertest.model

import com.sergio.mozpertest.model.dto.Business
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BusinessRepositoryImpl : BusinessRepository {
    private val businessService: BusinessService

    override suspend fun fetchBusiness(): Response<Business> = businessService.getBusiness()

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        businessService = retrofit.create(BusinessService::class.java)
    }

    private companion object {
        const val BASE_URL = " https://demo3535907.mockable.io/"
    }
}
