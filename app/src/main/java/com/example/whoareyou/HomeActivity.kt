package com.example.whoareyou

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    private val image = arrayListOf(
        R.drawable.ic_profile,
        R.drawable.ic_profile_faker,
        R.drawable.ic_profile_android,
        R.drawable.ic_profile_android2,
        R.drawable.ic_profile_android3
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val id = findViewById<TextView>(R.id.tv_id)
        val name = findViewById<TextView>(R.id.tv_name)
        val age = findViewById<TextView>(R.id.tv_age)
        val mbti = findViewById<TextView>(R.id.tv_mbti)
        val btnFinished = findViewById<Button>(R.id.btn_finish)
        val icProfile = findViewById<ImageView>(R.id.ic_profile)

        id.text = intent.getStringExtra("id").toString()
        name.text = intent.getStringExtra("name").toString()
        age.text = intent.getStringExtra("age").toString()
        mbti.text = intent.getStringExtra("mbti").toString()

        icProfile.setImageResource(image.random())

        btnFinished.setOnClickListener {
            finish()
        }

    }
}