package com.example.a01_03


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class Thried_Activity : AppCompatActivity() {
    private lateinit var textResult: TextView
    private lateinit var textMeters: TextView
    private lateinit var btnReg: Button
    private lateinit var btnBack: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thried)
        textMeters = findViewById(R.id.textMeters)
        textResult = findViewById(R.id.textResult)
        btnReg = findViewById(R.id.btnReg)
        btnBack = findViewById(R.id.btnBack)

        val result = intent.getDoubleExtra("result", 0.0)
        val meters = intent.getDoubleExtra("meters", 0.0)

        textResult.text = String.format("%.0f тыс. руб.", result)
        textMeters.text = String.format("%.0f м²", meters)

        btnReg.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
        btnBack.setOnClickListener {
            finish()
        }
    }
}