package com.sergio.mozpertest.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import com.sergio.mozpertest.databinding.ActivityEmployeePagerBinding
import com.sergio.mozpertest.model.local.LocalEmployee
import com.sergio.mozpertest.ui.base.BaseActivity
import com.sergio.mozpertest.ui.detail.adapter.EmployeePagerAdapter
import com.sergio.mozpertest.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class EmployeeDetailActivity : BaseActivity<ActivityEmployeePagerBinding>() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var employeeUID: String

    private val observer = viewStateObserverOf<List<LocalEmployee>>(
        { employeeList -> loadEmployees(employeeList) },
        { viewModel.load() }
    )

    override fun getViewBinding() = ActivityEmployeePagerBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        employeeUID = getParameter(intent, EMPLOYEE_UID)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initViewModel()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun initViewModel() {
        viewModel.employeeList.observe(this, observer)
        viewModel.load()
    }

    private fun loadEmployees(data: List<LocalEmployee>?) {
        data?.let { employees ->
            binding.apply {
                val employeeAdapter = EmployeePagerAdapter(this@EmployeeDetailActivity, employees)
                employeePager.adapter = employeeAdapter
                employeePager.setCurrentItem(
                    getEmployeePosition(employees),
                    false
                )
            }
        }
    }

    private fun getEmployeePosition(employees: List<LocalEmployee>): Int {
        val response = employees.indexOf(
            employees.find {
                it.employeeUID.toString() == employeeUID
            }
        )
        return if (response > 0) response else 0
    }

    companion object {
        private const val EMPLOYEE_UID = "employee_uid"

        fun getDeepLink(employee_uid: Int): String {
            return "mozper://employee/detail?$EMPLOYEE_UID=$employee_uid"
        }
    }
}
