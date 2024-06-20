package com.example.bottomnavyt

import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class PatientViewHolder(view: View):ViewHolder(view){
    val tv_patientName:TextView = view.findViewById(R.id.tvPatietNAME)
    val tv_patientNIC:TextView = view.findViewById(R.id.tvPatientNIC)
    val tv_patientAddress:TextView = view.findViewById(R.id.tvPatientADDRESS)
    val tv_patientAge:TextView = view.findViewById(R.id.tvPatientAG)
    val tv_patientDob:TextView = view.findViewById(R.id.tvPatientDOB)
    val tv_patientReason:TextView = view.findViewById(R.id.tvPATIENTReason)
    val ivDelete:ImageView = view.findViewById(R.id.imgDelete)
    val cbCheck:CheckBox = view.findViewById(R.id.chkPatient)


}