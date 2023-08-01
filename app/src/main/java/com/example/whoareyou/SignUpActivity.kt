package com.example.whoareyou

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val id = findViewById<EditText>(R.id.edt_id)
        val pw = findViewById<EditText>(R.id.edt_pw)
        val name = findViewById<EditText>(R.id.edt_name)
        val age = findViewById<EditText>(R.id.edt_age)
        val mbti = findViewById<EditText>(R.id.edt_mbti)
        val btnSignUp = findViewById<Button>(R.id.btn_sign)

        btnSignUp.setOnClickListener {

            if (id.text.toString().isEmpty() || pw.text.toString().isEmpty() || name.text.toString().isEmpty()) {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
            } else {
                val homeIntent = Intent(this, SignInActivity::class.java)
                    .putExtra("id", id.text.toString())
                    .putExtra("name", name.text.toString())
                    .putExtra("age", age.text.toString())
                    .putExtra("mbti", mbti.text.toString())

                startActivity(homeIntent)
            }


        }

    }
}