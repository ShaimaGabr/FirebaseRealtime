package com.example.firebaserealtime.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import com.example.firebaserealtime.data.model.EmployeeModel
import com.example.firebaserealtime.R
import com.example.firebaserealtime.databinding.ActivityInsertionBinding
import com.example.firebaserealtime.ui.fetching.ViewModelFetching
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InsertionActivity : AppCompatActivity() {
    lateinit var binding: ActivityInsertionBinding
    private val ViewModel: ViewModelFetching by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertionBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnSave.setOnClickListener {
            saveEmployeeData()
        }
    }

    private fun saveEmployeeData() {

        //getting values
        val empName = binding.etEmpName.text.toString()
        val empAge = binding.etEmpAge.text.toString()
        val empSalary = binding.etEmpSalary.text.toString()

        if (empName.isEmpty()) {
            binding.etEmpName.error = "Please enter name"
        }
        if (empAge.isEmpty()) {
            binding.etEmpAge.error = "Please enter age"
        }
        if (empSalary.isEmpty()) {
            binding.etEmpSalary.error = "Please enter salary"
        } else {

            val employee = EmployeeModel(null, empName, empAge, empSalary)
            ViewModel.insertAction(employee)
            ViewModel.action.observe(this) {

                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }


    }
}