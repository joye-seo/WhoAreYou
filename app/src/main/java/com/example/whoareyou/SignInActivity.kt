package com.example.whoareyou

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)


        val id = findViewById<EditText>(R.id.edt_id_sign_in)
        val pw = findViewById<EditText>(R.id.edt_pw_sign_in)
        val btnLogin = findViewById<Button>(R.id.btn_login_sign_in)
        val btnSignUp = findViewById<Button>(R.id.btn_sign_sign_in)


        id.setText(intent.getStringExtra("id").toString())
//        //왜 setText를 굳이 써줘야할까?
//        val id = intent.getStringExtra("id").toString()
        val name = intent.getStringExtra("name").toString()
        val age = intent.getStringExtra("age").toString()
        val mbti = intent.getStringExtra("mbti").toString()



        btnLogin.setOnClickListener {

            if (id.text.toString().isEmpty() || pw.text.toString().isEmpty()) {
                Toast.makeText(this, "로그인을 할 수 없습니다. \n아이디/비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
            } else {
                val loginIntent =
                    Intent(this, HomeActivity::class.java)
                        .putExtra("id", id.text.toString())
                        .putExtra("name", name)
                        .putExtra("age", age)
                        .putExtra("mbti", mbti)
                startActivity(loginIntent)
                Toast.makeText(this, "로그인성공", Toast.LENGTH_SHORT).show()
            }


        }

        btnSignUp.setOnClickListener {
            val signInIntent =
                Intent(this, SignUpActivity::class.java)
            startActivity(signInIntent)
        }

    }
}