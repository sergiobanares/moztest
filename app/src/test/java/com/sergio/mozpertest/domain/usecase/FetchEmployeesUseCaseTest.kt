package com.sergio.mozpertest.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sergio.mozpertest.base.BaseTest
import com.sergio.mozpertest.model.repository.BusinessRepository
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class FetchEmployeesUseCaseTest : BaseTest() {


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var useCase: FetchEmployeesUseCase

    @MockK
    private lateinit var repository: BusinessRepository

    private val coroutineDispatcher = UnconfinedTestDispatcher()

    @Before
    override fun setup() {
        super.setup()
        Dispatchers.setMain(coroutineDispatcher)
        useCase = FetchEmployeesUseCase(repository)
    }

    @After
    override fun onClear() {
        super.onClear()
        Dispatchers.resetMain()
    }

    @Test
    fun whenFetchEmployeesIsSuccess() = runTest {

    }

}