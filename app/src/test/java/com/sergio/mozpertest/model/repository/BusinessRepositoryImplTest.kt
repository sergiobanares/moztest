package com.sergio.mozpertest.model.repository

import com.sergio.mozpertest.base.BaseTest
import com.sergio.mozpertest.model.local.EmployeeDao
import com.sergio.mozpertest.model.local.LocalEmployee
import com.sergio.mozpertest.model.remote.BusinessService
import com.sergio.mozpertest.model.remote.dto.BusinessDTO
import com.sergio.mozpertest.model.remote.dto.EmployeeDTO
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
internal class BusinessRepositoryTest : BaseTest() {

    @RelaxedMockK
    private lateinit var service: BusinessService

    @RelaxedMockK
    private lateinit var employeeDao: EmployeeDao

    private lateinit var repositoryImpl: BusinessRepositoryImpl

    private val coroutineDispatcher = UnconfinedTestDispatcher()

    private val emptyList = listOf<LocalEmployee>()
    private val localList = listOf(fakeEmployee().toLocalEmployee())
    private val remoteList = listOf(fakeEmployee(), fakeEmployee(), fakeEmployee())

    @Before
    override fun setup() {
        super.setup()
        Dispatchers.setMain(coroutineDispatcher)
        repositoryImpl = BusinessRepositoryImpl(
            service,
            employeeDao,
            coroutineDispatcher
        )

    }

    @After
    override fun onClear() {
        super.onClear()
        Dispatchers.resetMain()
    }

    @Test
    fun testLocalSuccessCase() = runTest {
        coEvery { employeeDao.getAllEmployees() } returns localList
        val employees = repositoryImpl.getEmployees()
        Assert.assertEquals(localList, employees)
    }

    @Test
    fun testRemoteSuccessCase() = runTest {
        coEvery { employeeDao.getAllEmployees() } returns emptyList

        val fakeApiResponse = Response.success(
            BusinessDTO(
                remoteList
            )
        )
        coEvery { service.getBusiness() } returns fakeApiResponse
        val employees = repositoryImpl.getEmployees()
        Assert.assertEquals(
            remoteList.map { it.toLocalEmployee() },
            employees
        )
    }


    private fun fakeEmployee() = EmployeeDTO(
        "description",
        "firstName",
        1,
        "image",
        "lastName",
        3.5
    )

}
