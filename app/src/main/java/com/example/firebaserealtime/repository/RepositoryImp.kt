package com.example.firebaserealtime.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.firebaserealtime.data.model.EmployeeModel
import com.google.firebase.database.*

class RepositoryImp(

    var database: DatabaseReference


) : TaskRepository {
    val empList = arrayListOf<EmployeeModel>()


    override suspend fun getTasks(liveData: MutableLiveData<List<EmployeeModel>>) {


        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                empList.clear()
                if (snapshot.exists()) {
                    for (empSnap in snapshot.children) {
                        val empData = empSnap.getValue(EmployeeModel::class.java)
                        empList.add(empData!!)
                        liveData.postValue(empList)
                        Log.d("testApp", empList.toString())
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    var IdUnite: String = ""
    override suspend fun insertTasks(data: EmployeeModel, action: MutableLiveData<String>) {

        if (data.empId == null) {
            IdUnite = database.push().key!!
        } else {
            IdUnite = data.empId!!
        }
        database.child(IdUnite)
            .setValue(EmployeeModel(IdUnite, data.empName, data.empAge, data.empSalary))
            .addOnCompleteListener {
                action.postValue("true")
            }.addOnFailureListener { err ->

                action.postValue("false")
            }
    }

    override suspend fun deletTask(id: String, action: MutableLiveData<String>) {
        val remove = database.child(id).removeValue()
        remove.addOnSuccessListener {
            action.postValue("true")
        }.addOnFailureListener {
            action.postValue("false")
        }
    }


}