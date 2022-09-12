package com.sergio.mozpertest.util

import com.sergio.mozpertest.model.remote.dto.EmployeeDTO

object EmployeeUtil {

    fun fakeEmployee() = EmployeeDTO(
        "description",
        "firstName",
        1,
        "image",
        "lastName",
        3.5
    )
}