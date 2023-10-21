package com.example.whoareyou

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore

var isSignUp = false

class SignUpActivity : AppCompatActivity() {

    private lateinit var mFirebaseAuth: FirebaseAuth // ㅍㅏ이어 베이스 인증
    private lateinit var mdatabaseRef: DatabaseReference // 실시간 데이터베이스

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val id = findViewById<EditText>(R.id.edt_id)
        val pw = findViewById<EditText>(R.id.edt_pw)
        val name = findViewById<EditText>(R.id.edt_name)
        val age = findViewById<EditText>(R.id.edt_age)
        val mbti = findViewById<EditText>(R.id.edt_mbti)
        val btnSignUp = findViewById<Button>(R.id.btn_sign)
        val btnBack = findViewById<ImageView>(R.id.btn_sign_up_back)

        val errorId = findViewById<TextView>(R.id.tv_error_id)
        val errorPw = findViewById<TextView>(R.id.tv_error_pw)

        val fireStore = com.google.firebase.ktx.Firebase.firestore

        //파이어 베이스 관련

        mFirebaseAuth = Firebase.auth
//        mFirebaseAuth = FirebaseAuth.getInstance()
        mdatabaseRef = FirebaseDatabase.getInstance().reference


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

                //파이어베이스 auth 진행
                mFirebaseAuth.createUserWithEmailAndPassword(userId, userPassword).addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        val mFirebaseUser = mFirebaseAuth.currentUser
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")

                        //setValue가 데이터베이스에 값을 삽입함!
                        if (mFirebaseUser != null) {
                            mdatabaseRef.child("User").child(mFirebaseUser.uid)
                                .setValue(UserAccount(userId, userPassword))
                        }



                        Toast.makeText(this, "성공했습니다", Toast.LENGTH_SHORT).show()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", it.exception)
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }

                    PreferenceUtil(applicationContext).setString(userId, userPassword, userName, userAge, userMbti)

                    val homeIntent = Intent(this, SignInActivity::class.java)
                        .putExtra("id", id.text.toString())
                        .putExtra("pw", pw.text.toString())
                    setResult(RESULT_OK, homeIntent)

                    finish()

                }
            } else {
                Toast.makeText(this, "조건에 맞게 작성해주세요", Toast.LENGTH_SHORT).show()
            }
        }

        btnBack.setOnClickListener {
            finish()
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