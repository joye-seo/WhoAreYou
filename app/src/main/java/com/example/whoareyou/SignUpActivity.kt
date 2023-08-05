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

lateinit var pw: EditText

lateinit var errorId: TextView
lateinit var errorPw: TextView

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val id = findViewById<EditText>(R.id.edt_id)
        pw = findViewById<EditText>(R.id.edt_pw)
        val name = findViewById<EditText>(R.id.edt_name)
        val age = findViewById<EditText>(R.id.edt_age)
        val mbti = findViewById<EditText>(R.id.edt_mbti)
        val btnSignUp = findViewById<Button>(R.id.btn_sign)

        errorId = findViewById(R.id.tv_error_id)
        errorPw = findViewById(R.id.tv_error_pw)

        pw.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                editTextIdError()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        btnSignUp.setOnClickListener {


            if (id.text.toString().isEmpty() || pw.text.toString().isEmpty() || name.text.toString().isEmpty()) {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
            } else {
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

                Log.d("testMemo", sharedPreference.getString("id", "").toString())
            }
        }

    }
}

fun editTextIdError() {

    if (pw.length() < 10) {
        errorPw.visibility = View.VISIBLE
    } else {
        errorPw.visibility = View.GONE
    }
}