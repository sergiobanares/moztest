package com.sergio.mozpertest.ui.main.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.sergio.mozpertest.R
import com.sergio.mozpertest.databinding.EmployeeRowBinding
import com.sergio.mozpertest.extension.px
import com.sergio.mozpertest.model.local.LocalEmployee
import com.sergio.mozpertest.ui.main.listener.OnEmployeeClickListener

class EmployeeViewHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {

    fun bindData(localEmployee: LocalEmployee, listener: OnEmployeeClickListener) {
        EmployeeRowBinding.bind(view).apply {
            employeeRowImage.load(localEmployee.image) {
                crossfade(true)
                placeholder(R.drawable.image_placeholder)
                transformations(RoundedCornersTransformation(16.px.toFloat()))
            }
            employeeRowName.text = "Lastname: ${localEmployee.lastName}"
            employeeRowDetail.text = "FirstName: ${localEmployee.firstName}"
            root.setOnClickListener {
                listener.onEmployeeClicked(localEmployee)
            }
        }
    }
}
