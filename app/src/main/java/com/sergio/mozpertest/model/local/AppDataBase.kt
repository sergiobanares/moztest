package com.sergio.mozpertest.model.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        LocalEmployee::class
    ],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao
}
