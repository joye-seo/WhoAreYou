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

        val sharedPreference = getSharedPreferences("other", 0)

        val id = findViewById<TextView>(R.id.tv_id)
        val name = findViewById<TextView>(R.id.tv_name)
        val age = findViewById<TextView>(R.id.tv_age)
        val mbti = findViewById<TextView>(R.id.tv_mbti)
        val btnFinished = findViewById<Button>(R.id.btn_finish)
        val icProfile = findViewById<ImageView>(R.id.ic_profile)

        id.text = sharedPreference.getString("id", "")
        name.text = sharedPreference.getString("name", "")
        age.text = sharedPreference.getString("age", "")
        mbti.text = sharedPreference.getString("mbti", "")

        icProfile.setImageResource(image.random())

        btnFinished.setOnClickListener {
            finish()
        }

    }
}