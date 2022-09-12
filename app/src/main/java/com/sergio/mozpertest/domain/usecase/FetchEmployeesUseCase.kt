package com.sergio.mozpertest.domain.usecase

import com.sergio.mozpertest.domain.Resource
import com.sergio.mozpertest.domain.Resource.Fail
import com.sergio.mozpertest.domain.Resource.Loading
import com.sergio.mozpertest.domain.Resource.Success
import com.sergio.mozpertest.model.local.LocalEmployee
import com.sergio.mozpertest.model.repository.BusinessRepository
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class FetchEmployeesUseCase @Inject constructor(
    private val repository: BusinessRepository
) {
    operator fun invoke(): Flow<Resource<List<LocalEmployee>>> = flow {
        try {
            emit(Loading<List<LocalEmployee>>())
            val response = repository.getEmployees()
            if (response.isNotEmpty()){
                emit(Success<List<LocalEmployee>>(response))
            } else {
                emit(Fail<List<LocalEmployee>>(message = "no employees found"))
            }
        } catch (e: HttpException) {
            emit(
                Fail<List<LocalEmployee>>(
                    message = e.localizedMessage ?: "HttpException error occurred"
                )
            )
        } catch (e: IOException) {
            emit(Fail<List<LocalEmployee>>(message = "IOException error occurred"))
        }
    }
}
