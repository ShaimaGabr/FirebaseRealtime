package com.example.firebaserealtime.ui

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.example.firebaserealtime.data.model.EmployeeModel
import com.example.firebaserealtime.R
import com.example.firebaserealtime.databinding.ActivityEmployeeDetailsBinding
import com.example.firebaserealtime.databinding.UpdateDialogBinding
import com.example.firebaserealtime.ui.fetching.FetchingActivity
import com.example.firebaserealtime.ui.fetching.ViewModelFetching
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeeDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityEmployeeDetailsBinding
    private val viewModelDelet: ViewModelFetching by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setValuesToViews()
        binding.btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("empId").toString(),
                intent.getStringExtra("empName").toString()
            )
        }
        binding.btnDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("empId").toString()
            )
        }

    }


    private fun deleteRecord(
        id: String
    ) {
        viewModelDelet.delete(id)
        viewModelDelet.deleted.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun openUpdateDialog(
        empId: String,
        empName: String
    ) {
        val mDialog = AlertDialog.Builder(this)
        val bindingUpdat = UpdateDialogBinding.inflate(layoutInflater)


        mDialog.setView(bindingUpdat.root)

        val etEmpName = bindingUpdat.etEmpName
        val etEmpAge = bindingUpdat.etEmpAge
        val etEmpSalary = bindingUpdat.etEmpSalary

        val btnUpdateData = bindingUpdat.btnUpdateData

        etEmpName.setText(intent.getStringExtra("empName").toString())
        etEmpAge.setText(intent.getStringExtra("empAge").toString())
        etEmpSalary.setText(intent.getStringExtra("empSalary").toString())

        mDialog.setTitle("Updating $empName Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updateEmpData(
                empId,
                etEmpName.text.toString(),
                etEmpAge.text.toString(),
                etEmpSalary.text.toString()
            )


            //we are setting updated data to our textviews
            binding.tvEmpName.text = etEmpName.text.toString()
            binding.tvEmpAge.text = etEmpAge.text.toString()
            binding.tvEmpSalary.text = etEmpSalary.text.toString()

            alertDialog.dismiss()
        }
    }

    private fun updateEmpData(
        id: String,
        name: String,
        age: String,
        salary: String
    ) {
        viewModelDelet.insertAction(EmployeeModel(id, name, age, salary))
        viewModelDelet.action.observe(this) {
            Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
        }
    }


    private fun setValuesToViews() {
        binding.tvEmpId.text = intent.getStringExtra("empId")
        binding.tvEmpName.text = intent.getStringExtra("empName")
        binding.tvEmpAge.text = intent.getStringExtra("empAge")
        binding.tvEmpSalary.text = intent.getStringExtra("empSalary")

    }
}