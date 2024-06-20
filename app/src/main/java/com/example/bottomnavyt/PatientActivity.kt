package com.example.bottomnavyt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavyt.database.Patient
import com.example.bottomnavyt.database.PatientDatabase
import com.example.bottomnavyt.database.PatientRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PatientActivity : AppCompatActivity() {

    private lateinit var adapter:PatientAdapter
    private lateinit var viewModel:PatientActivityData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient)

        val recyclerView: RecyclerView = findViewById(R.id.rvPatientsList)
        val repository = PatientRepository(PatientDatabase.getInstance(this))
        viewModel = ViewModelProvider(this)[PatientActivityData::class.java]

        viewModel.data.observe(this){
            adapter = PatientAdapter(it,repository,viewModel)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this)
        }

        CoroutineScope(Dispatchers.IO).launch {
            val data = repository.getAllPatients()

            runOnUiThread{
                viewModel.setData(data)
            }
        }

        val addPatient:FloatingActionButton = findViewById(R.id.fabAddPatient)
        val backToHome:FloatingActionButton = findViewById(R.id.fabBackToHome)
        addPatient.setOnClickListener{
            displayAlert(repository)
        }

        backToHome.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

    }

    private fun displayAlert(repository:PatientRepository){
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.form_input_layout, null)
        builder.setView(dialogView)

        builder.setTitle(getText(R.string.alertTitle))
        builder.setMessage(getText(R.string.alertMsg))

        builder.setPositiveButton("OK") { dialog, which ->
            val inputName = dialogView.findViewById<EditText>(R.id.edtPatientName)
            val name = inputName.text.toString()

            val inputNIC = dialogView.findViewById<EditText>(R.id.edtPatientNic)
            val nic = inputNIC.text.toString()

            val inputAddress = dialogView.findViewById<EditText>(R.id.edtPatientAddress)
            val address = inputAddress.text.toString()

            val inputAge = dialogView.findViewById<EditText>(R.id.edtPatientAge)
            val age = inputAge.text.toString()

            val inputDOB = dialogView.findViewById<EditText>(R.id.edtPatientDob)
            val dob = inputDOB.text.toString()

            val inputReason = dialogView.findViewById<EditText>(R.id.edtReason)
            val reason = inputReason.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                repository.insert(Patient(name,nic,address,age,dob,reason))
                val data = repository.getAllPatients()
                runOnUiThread {
                    viewModel.setData(data)
                }
            }
        }

        builder.setNegativeButton("CANCEL") { dialog, which ->
            dialog.cancel()
        }
        val alertDialog = builder.create()
        alertDialog.show()

    }
}