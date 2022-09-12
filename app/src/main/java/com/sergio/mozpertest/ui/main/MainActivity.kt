package com.sergio.mozpertest.ui.main


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sergio.mozpertest.R
import com.sergio.mozpertest.databinding.ActivityMainBinding
import com.sergio.mozpertest.domain.login.LoginManager
import com.sergio.mozpertest.model.local.LocalEmployee
import com.sergio.mozpertest.ui.base.BaseActivity
import com.sergio.mozpertest.ui.detail.EmployeeDetailActivity
import com.sergio.mozpertest.ui.login.LoginActivity
import com.sergio.mozpertest.ui.main.adapter.EmployeeAdapter
import com.sergio.mozpertest.ui.main.listener.OnEmployeeClickListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
internal class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var loginManager: LoginManager

    private val observer = viewStateObserverOf<List<LocalEmployee>>(
        { employeeList -> loadEmployees(employeeList) },
        { viewModel.load() }
    )

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureLayout()
        initViewModel()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        return if (id == R.id.action_exit) {
            loginManager.logout()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actions, menu)
        return true
    }

    private fun configureLayout() {
        binding.employeesRecycler.layoutManager = LinearLayoutManager(this@MainActivity)
    }

    private fun initViewModel() {
        viewModel.employeeList.observe(this, observer)
        viewModel.load()
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
            navigateTo(
                this@MainActivity,
                EmployeeDetailActivity.getDeepLink(employee.employeeUID)
            )
        }
    }
}
