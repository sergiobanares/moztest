package com.sergio.mozpertest.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sergio.mozpertest.R
import com.sergio.mozpertest.model.local.LocalEmployee
import com.sergio.mozpertest.ui.main.listener.OnEmployeeClickListener

class EmployeeAdapter(
    private val employeeList: List<LocalEmployee>,
    private val listener: OnEmployeeClickListener
) : RecyclerView.Adapter<EmployeeViewHolder>() { // TODO: upgrade to ListAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return EmployeeViewHolder(
            layoutInflater.inflate(
                R.layout.employee_row,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = employeeList.size

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.bindData(employeeList[position], listener)
    }
}
