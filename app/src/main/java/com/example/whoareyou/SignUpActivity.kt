package com.example.whoareyou

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

var isSignUp = false

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

        val errorId = findViewById<TextView>(R.id.tv_error_id)
        val errorPw = findViewById<TextView>(R.id.tv_error_pw)

        id.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                editTextError(id, errorId, 4, 20)
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        pw.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                editTextError(pw, errorPw, 8, Int.MAX_VALUE)
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        btnSignUp.setOnClickListener {

            val userId = id.text.toString()
            val userPassword = pw.text.toString()
            val userName = name.text.toString()
            val userAge = age.text.toString()
            val userMbti = mbti.text.toString()


            if (userId.trim().isEmpty() || userPassword.trim().isEmpty() || userName.trim().isEmpty()) {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()

            } else if (isSignUp) {

                PreferenceUtil(applicationContext).setString(userId, userPassword, userName, userAge, userMbti)

                val homeIntent = Intent(this, SignInActivity::class.java)
                    .putExtra("id", id.text.toString())
                    .putExtra("pw", pw.text.toString())
                setResult(RESULT_OK, homeIntent)

                finish()

            } else {
                Toast.makeText(this, "조건에 맞게 작성해주세요", Toast.LENGTH_SHORT).show()
            }
        }

    }
}

fun editTextError(name: EditText, errorName: TextView, min: Int, max: Int) {
    if (name.length() in min..max) {
        errorName.visibility = View.GONE
        isSignUp = true
    } else {
        errorName.visibility = View.VISIBLE
        isSignUp = false
    }
}