package com.example.bottomnavyt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PresTemplateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pres_template)

        val btnBack:FloatingActionButton = findViewById(R.id.fagBACK)
        btnBack.setOnClickListener{
            val intent = Intent(this, Prescription::class.java)
            startActivity(intent)
        }
    }
}