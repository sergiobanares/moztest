package com.sergio.mozpertest.model.repository

import com.sergio.mozpertest.model.local.EmployeeDao
import com.sergio.mozpertest.model.local.LocalEmployee
import com.sergio.mozpertest.model.remote.BusinessService
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BusinessRepositoryImpl @Inject constructor(
    private val api: BusinessService,
    private val localEmployeeDB: EmployeeDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : BusinessRepository {

    override suspend fun getEmployees(): List<LocalEmployee> =
        withContext(dispatcher) {
            var localEmployees = localEmployeeDB.getAllEmployees()
            if (localEmployees.isEmpty()) {
                val business = api.getBusiness()
                if (business.isSuccessful) {
                    val data = business.body()!!.employees
                    localEmployees = data.map {
                        it.toLocalEmployee()
                    }
                    localEmployeeDB.insertAllEmployees(
                        *localEmployees.toTypedArray()
                    )
                }
            }
            return@withContext localEmployees
        }

    /*override suspend fun getEmployeeDetail(employeeUID: String): LocalEmployee =
        withContext(dispatcher) {
            return@withContext localEmployeeDB.getEmployee(employeeUID)
        }
     */

}
