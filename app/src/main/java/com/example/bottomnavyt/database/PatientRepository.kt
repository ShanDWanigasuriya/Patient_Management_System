package com.example.bottomnavyt.database

class PatientRepository(
    private val db:PatientDatabase
) {
    suspend fun insert(patient: Patient) = db.getPatientDao().insert(patient)
    suspend fun delete(patient: Patient) = db.getPatientDao().delete(patient)
    fun getAllPatients():List<Patient> = db.getPatientDao().getAllPatients()
}