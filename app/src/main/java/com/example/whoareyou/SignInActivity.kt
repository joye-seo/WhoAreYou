package com.example.whoareyou

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

//        val id = findViewById<EditText>(R.id.edt_id_sign_in)
//        val pw = findViewById<EditText>(R.id.edt_pw_sign_in)
        val btnLogin = findViewById<Button>(R.id.btn_login_sign_in)
        val btnSignUp = findViewById<Button>(R.id.btn_sign_sign_in)

        val id = intent.getStringExtra("id").toString()
        val name = intent.getStringExtra("name").toString()
        val age = intent.getStringExtra("age").toString()
        val mbti = intent.getStringExtra("mbti").toString()


        btnLogin.setOnClickListener {
            val loginIntent =
                Intent(this, HomeActivity::class.java)
                    .putExtra("id", id)
                    .putExtra("name", name)
                    .putExtra("age", age)
                    .putExtra("mbti", mbti)
            startActivity(loginIntent)
        }

        btnSignUp.setOnClickListener {
            val signInIntent =
                Intent(this, SignUpActivity::class.java)
            startActivity(signInIntent)
        }

    }
}