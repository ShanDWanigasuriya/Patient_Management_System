package com.example.bottomnavyt.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Patient(
    var pName: String?,
    var pNic: String?,
    var pAddress: String?,
    var pAge: String?,
    var pDob: String?,
    var pReason: String?
){
    @PrimaryKey(autoGenerate = true)
    var id: Int?= null
}
