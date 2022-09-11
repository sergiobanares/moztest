package com.sergio.mozpertest.model.repository

import com.sergio.mozpertest.model.local.EmployeeDao
import com.sergio.mozpertest.model.local.LocalEmployee
import com.sergio.mozpertest.model.remote.BusinessService
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BusinessRepositoryImpl @Inject constructor(
    private val api: BusinessService,
    private val localEmployeeDB: EmployeeDao
) : BusinessRepository {

    override suspend fun getEmployees(): List<LocalEmployee> =
        withContext(Dispatchers.IO) {
            val localEmployees = localEmployeeDB.getAllEmployees()
            if (localEmployees.isEmpty()) {
                val business = api.getBusiness()
                if (business.isSuccessful) {
                    val data = business.body()!!.employees
                    localEmployeeDB.insertAlEmployees(*data.map { it.toLocalEmployee() }
                        .toTypedArray())
                }
            }
            return@withContext localEmployees
        }

    override suspend fun getEmployeeDetail(employeeUID: String): LocalEmployee =
        withContext(Dispatchers.IO) {
            return@withContext localEmployeeDB.getEmployee(employeeUID)
        }

}
