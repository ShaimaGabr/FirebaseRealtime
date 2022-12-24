package com.example.firebaserealtime.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaserealtime.data.model.EmployeeModel
import com.example.firebaserealtime.databinding.EmpListItemBinding

class AdapterAnother: RecyclerView.Adapter<AdapterAnother.ViewHolder>() {
    lateinit var onOverItemClick:((EmployeeModel)->Unit)
    private val diffutil=object : DiffUtil.ItemCallback<EmployeeModel>(){
        override fun areItemsTheSame(oldItem: EmployeeModel, newItem: EmployeeModel): Boolean {
            return oldItem.empId==newItem.empId
        }

        override fun areContentsTheSame(oldItem: EmployeeModel, newItem:EmployeeModel): Boolean {
            return oldItem==newItem
        }
    }
    val differ= AsyncListDiffer(this,diffutil)
    class ViewHolder(val binding:EmpListItemBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(EmpListItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data=differ.currentList[position]

        holder.binding.tvEmpName.text=data.empName
        holder.itemView.setOnClickListener {

            onOverItemClick.invoke(data)

        }
    }

    override fun getItemCount()=differ.currentList.size


}