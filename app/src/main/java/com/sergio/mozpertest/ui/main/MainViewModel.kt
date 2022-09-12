package com.sergio.mozpertest.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergio.mozpertest.domain.Resource
import com.sergio.mozpertest.domain.usecase.FetchEmployeesUseCase
import com.sergio.mozpertest.model.local.LocalEmployee
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: FetchEmployeesUseCase
) : ViewModel() {


    val employeeList: LiveData<Resource<List<LocalEmployee>>>
        get() = _employeeList
    private val _employeeList = MutableLiveData<Resource<List<LocalEmployee>>>()

    fun load() {
        useCase().onEach { result ->
            with(_employeeList) {
                when (result) {
                    is Resource.Success -> postValue(result)
                    is Resource.Fail -> postValue(Resource.Fail(message = "${result.message}"))
                    is Resource.Loading -> postValue(Resource.Loading())
                }
            }
        }.launchIn(viewModelScope)
    }

}
