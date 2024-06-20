package com.example.bottomnavyt

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.bottomnavyt.database.Patient
import com.example.bottomnavyt.database.PatientRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PatientAdapter(items:List<Patient>, repository:PatientRepository, viewModel:PatientActivityData) : Adapter<PatientViewHolder>(){

    var context:Context?=null
    val items = items
    val repository = repository
    val viewModel = viewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_item, parent,false)
        context = parent.context

        return PatientViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        holder.tv_patientName.text = items.get(position).pName
        holder.tv_patientNIC.text = items.get(position).pNic
        holder.tv_patientAddress.text = items.get(position).pAddress
        holder.tv_patientAge.text = items.get(position).pAge
        holder.tv_patientDob.text = items.get(position).pDob
        holder.tv_patientReason.text = items.get(position).pReason


        holder.ivDelete.setOnClickListener{
            val isChecked = holder.cbCheck.isChecked
            if(isChecked){
                CoroutineScope(Dispatchers.IO).launch{
                    repository.delete(items.get(position))
                    val data = repository.getAllPatients()
                    withContext(Dispatchers.Main){
                        viewModel.setData(data)
                    }
                }

                Toast.makeText(context,"DELETED RECORD NO.$position",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(context,"SELECT THE RECORD TO BE DELETED",Toast.LENGTH_LONG).show()
            }
        }


    }


}