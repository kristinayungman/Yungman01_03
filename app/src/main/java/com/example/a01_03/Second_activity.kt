package com.example.a01_03


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast

class Second_activity : AppCompatActivity() {
    private lateinit var spinnerType: Spinner
    private lateinit var editMeters: EditText
    private lateinit var btnRaschet: Button
    private lateinit var btnBack: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        spinnerType = findViewById(R.id.spinnerType)
        editMeters = findViewById(R.id.editMeters)
        btnRaschet = findViewById(R.id.btnRaschet)
        btnBack = findViewById(R.id.btnBack)

        btnRaschet.setOnClickListener {
            val metersText = editMeters.text.toString().trim()
            if (metersText.isEmpty()) {
                Toast.makeText(this, "Введите количество метров", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val meters = metersText.toDoubleOrNull()
            if (meters == null || meters <= 0) {
                Toast.makeText(this, "Введите корректное число метров", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val type = spinnerType.selectedItemPosition
            val pricePerMeter = 100.0

            var result = 0.0
            when (type) {
                0 -> result = pricePerMeter * meters * 1.4
                1 -> result = pricePerMeter * meters
                2 -> result = pricePerMeter * meters * 0.8
                3 -> result = pricePerMeter * meters * 1.1
            }

            val intent = Intent(this, Thried_Activity::class.java)
            intent.putExtra("result", result)
            intent.putExtra("meters", meters)
            startActivity(intent)
        }
        btnBack.setOnClickListener {
            finish()
        }
    }
}