package com.example.firebaserealtime.ui.fetching

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebaserealtime.data.model.EmployeeModel
import com.example.firebaserealtime.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ViewModelFetching @Inject constructor(
    val repository: TaskRepository
):ViewModel() {


    private val _tasks = MutableLiveData<List<EmployeeModel>>()
    val tasks: LiveData<List<EmployeeModel>> = _tasks


     fun getTasks() {

         viewModelScope.launch {
            repository.getTasks(_tasks)

     }}

    private val _action = MutableLiveData<String>()
    val action: LiveData<String> = _action

    fun insertAction(employeeModel : EmployeeModel){
        viewModelScope.launch {
        repository.insertTasks(employeeModel,_action)
    }}
    private val _deleted = MutableLiveData<String>()
    val deleted: LiveData<String> = _deleted

fun delete(id:String){
    viewModelScope.launch {
        repository.deletTask(id,_deleted)
    }
}
}


