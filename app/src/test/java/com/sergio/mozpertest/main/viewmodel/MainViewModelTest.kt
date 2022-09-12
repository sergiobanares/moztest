package com.sergio.mozpertest.main.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.sergio.mozpertest.base.BaseTest
import com.sergio.mozpertest.domain.Resource
import com.sergio.mozpertest.domain.usecase.FetchEmployeesUseCase
import com.sergio.mozpertest.model.local.LocalEmployee
import com.sergio.mozpertest.ui.main.MainViewModel
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest : BaseTest() {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var mainViewModel: MainViewModel

    @MockK
    private lateinit var responseObserver: Observer<Resource<List<LocalEmployee>>>

    @MockK
    private lateinit var useCase: FetchEmployeesUseCase

    private val coroutineDispatcher = UnconfinedTestDispatcher()

    @Before
    override fun setup() {
        super.setup()
        Dispatchers.setMain(coroutineDispatcher)
        mainViewModel = MainViewModel(useCase)

        mainViewModel.employeeList.observeForever(responseObserver)
    }

    @After
    override fun onClear() {
        super.onClear()
        Dispatchers.resetMain()
    }

}
