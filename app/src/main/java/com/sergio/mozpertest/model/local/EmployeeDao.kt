package com.sergio.mozpertest.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface EmployeeDao {

    @Insert(onConflict = REPLACE)
    fun insertAllEmployees(vararg localPhoto: LocalEmployee)

    @Query("SELECT * FROM localEmployee WHERE employee_uid LIKE :employeeUID")
    fun getEmployee(employeeUID: String): LocalEmployee

    @Query("SELECT * FROM localEmployee")
    fun getAllEmployees(): List<LocalEmployee>

}
