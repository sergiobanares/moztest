package com.sergio.mozpertest.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sergio.mozpertest.base.BaseTest
import com.sergio.mozpertest.domain.Resource
import com.sergio.mozpertest.model.repository.BusinessRepository
import com.sergio.mozpertest.util.EmployeeUtil.fakeEmployee
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
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
    fun whenExecutedLoadingStateFirst() = runTest {
        Assert.assertTrue(useCase().first() is Resource.Loading)
    }

    @Test
    fun when_empty_list_returns_fail_state() = runTest {
        coEvery { repository.getEmployees() } returns listOf()
        Assert.assertTrue(useCase().last() is Resource.Fail)
        Assert.assertTrue(useCase().last().message == NO_EMPLOYEES_MESSAGE)
    }

    @Test
    fun when_full_list_returns_success_state() = runTest {
        val employeeList = listOf(fakeEmployee().toLocalEmployee())
        coEvery { repository.getEmployees() } returns employeeList
        Assert.assertTrue(useCase().last() is Resource.Success)
        Assert.assertTrue(useCase().last().data == employeeList)
    }

    private companion object {
        const val NO_EMPLOYEES_MESSAGE = "no employees found"
    }


}