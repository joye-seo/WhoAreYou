package com.example.whoareyou

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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
//            val signUpIntent =
//                Intent(this, SignUpActivity::class.java).putExtra("id", id.toString()).putExtra("pw", pw.toString())
//                    .putExtra("age", age.toString()).putExtra("mbti", mbti.toString())

            val homeIntent = Intent(this, SignInActivity::class.java)
                .putExtra("id", id.text.toString())
                .putExtra("name", name.text.toString())
                .putExtra("age", age.text.toString())
                .putExtra("mbti", mbti.text.toString())

            startActivity(homeIntent)

        }

    }
}