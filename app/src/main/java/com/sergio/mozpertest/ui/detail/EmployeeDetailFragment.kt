package com.sergio.mozpertest.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.sergio.mozpertest.databinding.ActivityEmployeeDetailBinding
import com.sergio.mozpertest.model.local.LocalEmployee

internal class EmployeeDetailFragment(
    private val employee: LocalEmployee
) : Fragment() {

    private lateinit var binding: ActivityEmployeeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityEmployeeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(employee) {
            binding.apply {
                employeeDetailImage.load(image)
                employeeDetailFullName.text = "Full Name: $firstName $lastName"
                employeeDetailId.text = "ID: $employeeUID"
                employeeDetailDescription.text = "Description: $description"
                employeeDetailRating.text = "Rating: $rating"
            }
        }
    }
}
