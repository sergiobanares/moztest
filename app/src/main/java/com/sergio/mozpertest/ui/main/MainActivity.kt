package com.sergio.mozpertest.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sergio.mozpertest.databinding.ActivityMainBinding
import com.sergio.mozpertest.domain.Resource.Fail
import com.sergio.mozpertest.domain.Resource.Loading
import com.sergio.mozpertest.domain.Resource.Success
import com.sergio.mozpertest.model.local.LocalEmployee
import com.sergio.mozpertest.ui.main.adapter.EmployeeAdapter
import com.sergio.mozpertest.ui.main.listener.OnEmployeeClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureLayout()
        setupViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.load()
    }

    private fun setupViewModel() {
        viewModel.employeeList.observe(this) {
            hideLoading()
            when (it) {
                is Fail -> showErrorScreen(it.message)
                is Loading -> showLoadingView()
                is Success -> loadEmployees(it.data)
            }
        }


    }

    private fun loadEmployees(data: List<LocalEmployee>?) {
        data?.let {
            val employeeAdapter = EmployeeAdapter(data, employeeClickListener)
            binding.employeesRecycler.apply {
                adapter = employeeAdapter
                isClickable = true
            }
        }

    }

    private val employeeClickListener = object : OnEmployeeClickListener {
        override fun onEmployeeClicked(employee: LocalEmployee) {
            Toast.makeText(
                this@MainActivity,
                employee.firstName,
                Toast.LENGTH_SHORT
            ).show()
            /*navigateTo(
                this@MainActivity,
                EmployeeDetailActivity.getDeepLink(employee.employeeUID)
            )*/
        }
    }

    private fun showErrorScreen(message: String?) {
        Toast.makeText(this, "Message: $message", Toast.LENGTH_LONG).show()
    }

    private fun showLoadingView() {
        //Show Loading View
    }

    private fun hideLoading() {
        //Hide Loading View
    }

    private fun configureLayout() {
        binding.employeesRecycler.layoutManager = LinearLayoutManager(this@MainActivity)
    }
}