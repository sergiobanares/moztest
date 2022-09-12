package com.sergio.mozpertest.ui.detail.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sergio.mozpertest.model.local.LocalEmployee
import com.sergio.mozpertest.ui.detail.EmployeeDetailFragment

internal class EmployeePagerAdapter(
    fa: FragmentActivity,
    private val employees: List<LocalEmployee>
) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = employees.size

    override fun createFragment(
        position: Int
    ): Fragment = EmployeeDetailFragment(employees[position])
}
