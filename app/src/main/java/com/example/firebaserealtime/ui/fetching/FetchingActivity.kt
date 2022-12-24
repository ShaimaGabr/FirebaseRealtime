package com.example.firebaserealtime.ui.fetching

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.firebaserealtime.adapter.AdapterAnother
import com.example.firebaserealtime.data.model.EmployeeModel
import com.example.firebaserealtime.databinding.ActivityFetchingBinding
import com.example.firebaserealtime.ui.EmployeeDetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FetchingActivity : AppCompatActivity() {
    lateinit var binding: ActivityFetchingBinding
    private lateinit var mAdapterAnother: AdapterAnother
    private val fetchingViewModel: ViewModelFetching by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFetchingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAdapterAnother = AdapterAnother()

        fetchingViewModel.getTasks()
        fetchingViewModel.tasks.observe(this) {
            mAdapterAnother.differ.submitList(it)
            mAdapterAnother.notifyDataSetChanged()
            binding.tvLoadingData.text = it.size.toString()
        }
        binding.rvEmp.apply {
            adapter = mAdapterAnother
        }


        mAdapterAnother.onOverItemClick = { data ->

            val intent = Intent(this@FetchingActivity, EmployeeDetailsActivity::class.java)

            //put extras
            intent.putExtra("empId", data.empId)
            intent.putExtra("empName", data.empName)
            intent.putExtra("empAge", data.empAge)
            intent.putExtra("empSalary", data.empSalary)
            startActivity(intent)
        }

    }


}