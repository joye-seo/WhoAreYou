package com.example.whoareyou

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

lateinit var id: EditText
lateinit var pw: EditText

lateinit var errorId: TextView
lateinit var errorPw: TextView

var isSignUp = false

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        id = findViewById<EditText>(R.id.edt_id)
        pw = findViewById<EditText>(R.id.edt_pw)
        val name = findViewById<EditText>(R.id.edt_name)
        val age = findViewById<EditText>(R.id.edt_age)
        val mbti = findViewById<EditText>(R.id.edt_mbti)
        val btnSignUp = findViewById<Button>(R.id.btn_sign)

        errorId = findViewById(R.id.tv_error_id)
        errorPw = findViewById(R.id.tv_error_pw)

        id.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                editTextIdError()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        pw.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                editTextPwError()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        btnSignUp.setOnClickListener {


            if (id.text.toString().isEmpty() || pw.text.toString().isEmpty() || name.text.toString().isEmpty()) {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
            } else if (!isSignUp) {
                val sharedPreference = getSharedPreferences("other", 0)
                val editor = sharedPreference.edit()
                editor.putString("id", id.text.toString())
                editor.putString("pw", pw.text.toString())
                editor.putString("name", name.text.toString())
                editor.putString("age", age.text.toString())
                editor.putString("mbti", mbti.text.toString())
                editor.apply()

                val homeIntent = Intent(this, SignInActivity::class.java)
                    .putExtra("id", id.text.toString())
                    .putExtra("pw", pw.text.toString())
                setResult(RESULT_OK, homeIntent)
                finish()
            } else {
                Toast.makeText(this, "필수 정보를 읽고 수정하세요.", Toast.LENGTH_SHORT).show()

            }
        }

    }
}

fun editTextIdError() {

    if (id.length() in 5..20) {
        errorId.visibility = View.GONE
        isSignUp = true
    } else {
        errorId.visibility = View.VISIBLE
        isSignUp = false
    }
}

fun editTextPwError() {

    if (pw.length() < 10) {
        errorPw.visibility = View.VISIBLE
        isSignUp = true
    } else {
        errorPw.visibility = View.GONE
        isSignUp = false
    }
}