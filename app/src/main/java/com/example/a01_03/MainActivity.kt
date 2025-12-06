package com.example.a01_03


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    private val PREFS_FILE = "Account"
    private val PREF_LOGIN = "login"
    private val PREF_PASSWORD = "password"

    private lateinit var settings: SharedPreferences
    private lateinit var editLogin: EditText
    private lateinit var editPassword: EditText
    private lateinit var btnVhod: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        settings = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)

        editLogin = findViewById(R.id.editLogin)
        editPassword = findViewById(R.id.editPassword)
        btnVhod = findViewById(R.id.btnVhod)

        btnVhod.setOnClickListener {
            val login = editLogin.text.toString().trim()
            val password = editPassword.text.toString().trim()

            if (login.isEmpty() || password.isEmpty()) {
                AlertDialog.Builder(this)
                    .setTitle("Ошибка")
                    .setMessage("Введите логин и пароль")
                    .setPositiveButton("OK", null)
                    .show()
            } else {
                val savedLogin = settings.getString(PREF_LOGIN, null)
                val savedPassword = settings.getString(PREF_PASSWORD, null)

                if (savedLogin == null || savedPassword == null) {

                    settings.edit()
                        .putString(PREF_LOGIN, login)
                        .putString(PREF_PASSWORD, password)
                        .apply()
                    Toast.makeText(this, "Аккаунт создан! Запомните ваши данные.", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, Second_activity::class.java))
                } else {
                    if (login == savedLogin && password == savedPassword) {
                        startActivity(Intent(this, Second_activity::class.java))
                    } else {
                        Toast.makeText(this, "Неверный логин или пароль!", Toast.LENGTH_LONG).show()

                        editLogin.setText("")
                        editPassword.setText("")
                        editLogin.requestFocus()
                    }
                }
            }
        }
    }
}