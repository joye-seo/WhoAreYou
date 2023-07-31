package com.example.whoareyou

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val id = findViewById<TextView>(R.id.tv_id)
        val name = findViewById<TextView>(R.id.tv_name)
        val age = findViewById<TextView>(R.id.tv_age)
        val mbti = findViewById<TextView>(R.id.tv_mbti)
        val btnFinished = findViewById<Button>(R.id.btn_finish)

        id.text = intent.getStringExtra("id").toString()
        name.text = intent.getStringExtra("name").toString()
        age.text = intent.getStringExtra("age").toString()
        mbti.text = intent.getStringExtra("mbti").toString()

        btnFinished.setOnClickListener {
            finish()
        }

    }
}