package com.example.whoareyou

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


class SignInActivity : AppCompatActivity() {


    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    private lateinit var id: EditText
    private lateinit var pw: EditText

    private var login: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        id = findViewById<EditText>(R.id.edt_id_sign_in)
        pw = findViewById<EditText>(R.id.edt_pw_sign_in)
        val btnLogin = findViewById<Button>(R.id.btn_login_sign_in)
        val btnSignUp = findViewById<Button>(R.id.btn_sign_sign_in)
        val autoLogin = findViewById<CheckBox>(R.id.check_auto_login)

        setResultSignUp()

        btnLogin.setOnClickListener {

            val userId = id.text.toString()
            val userPassword = pw.text.toString()
            val signDataList = PreferenceUtil(applicationContext).getString()
            var index = 0

            for (i in signDataList) {
                //shared preference에 있는 값을 : 를 기준으로 자르고 각각 변수로 초기화 시킴
                val signUpContent = i.split(" : ")

                if (signUpContent[0] == userId && signUpContent[1] == userPassword) {

                    /**
                     * ++index 대신 list에 해당하는 인덱스 값을 불러오면 되는데..좀있다가 수정하쟈..ㅜㅜ!!
                     */

                    login = true
                    break
                } else {
                    ++index
                    login = false
                }
            }

            if (!login) {

                Toast.makeText(this, "아이디 또는 비밀번호를 확인해주세요!", Toast.LENGTH_SHORT).show()

            } else {
                val intent = Intent(this, HomeActivity::class.java).putExtra("index", index)
                startActivity(intent)
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                Log.d("testOperation1", signDataList[index])

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

                id.setText(resultId).toString()
                pw.setText(resultPw).toString()


            }
        }
    }
}
