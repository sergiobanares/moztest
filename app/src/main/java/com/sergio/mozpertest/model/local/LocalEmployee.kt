package com.sergio.mozpertest.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class LocalEmployee(
    @PrimaryKey
    @ColumnInfo(name = "employee_uid")
    val employeeUID: Int,
    val description: String,
    val firstName: String,
    val image: String,
    val lastName: String,
    val rating: Double
)

