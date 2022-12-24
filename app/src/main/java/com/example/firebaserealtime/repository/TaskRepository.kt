package com.example.firebaserealtime.repository

import androidx.lifecycle.MutableLiveData
import com.example.firebaserealtime.data.model.EmployeeModel

interface TaskRepository {
   suspend fun getTasks(liveData: MutableLiveData<List<EmployeeModel>>)
   suspend fun insertTasks(data: EmployeeModel,action:MutableLiveData<String>)
   suspend fun  deletTask(id:String,action:MutableLiveData<String>)
}