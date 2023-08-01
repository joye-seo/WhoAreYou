package com.example.whoareyou

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class SignInActivity : AppCompatActivity() {

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private lateinit var id: EditText
    private lateinit var pw: EditText
    var name = ""
    var age = ""
    var mbti = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)


        id = findViewById(R.id.edt_id_sign_in)
        pw = findViewById(R.id.edt_pw_sign_in)
        val btnLogin = findViewById<Button>(R.id.btn_login_sign_in)
        val btnSignUp = findViewById<Button>(R.id.btn_sign_sign_in)

        setResultSignUp()

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
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    private fun setResultSignUp() {
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->

            if (result.resultCode == RESULT_OK) {
                val resultId = result.data?.getStringExtra("id") ?: ""
                val resultPw = result.data?.getStringExtra("pw") ?: ""
                val resultName = result.data?.getStringExtra("name").toString()
                val resultAge = result.data?.getStringExtra("age").toString()
                val resultMbti = result.data?.getStringExtra("mbti").toString()

                id.setText(resultId).toString()
                pw.setText(resultPw).toString()
                name = resultName
                age = resultAge
                mbti = resultMbti

            }
        }
    }
}