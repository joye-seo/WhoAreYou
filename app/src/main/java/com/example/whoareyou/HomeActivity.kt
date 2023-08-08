package com.example.whoareyou

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class HomeActivity : AppCompatActivity() {

    private val image = arrayListOf(
        R.drawable.pf_img_1,
        R.drawable.pf_img_2,
        R.drawable.pf_img_3,
        R.drawable.pf_img_4,
        R.drawable.pf_img_5,
        R.drawable.pf_img_6,
        R.drawable.pf_img_7,
        R.drawable.pf_img_8,
        R.drawable.pf_img_9,
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

        val index = intent.getIntExtra("index", -1) // -1은 기본 값

        val signDataList = PreferenceUtil(applicationContext).getString()[index]

        val homeContent = signDataList.split(" : ")

        id.text = homeContent[0]
        name.text = homeContent[2]
        age.text = homeContent[3]
        mbti.text = homeContent[4]

        Glide.with(this)
            .load(image.random())
            .circleCrop()
            .into(icProfile)

        btnFinished.setOnClickListener{
            finish()
        }

    }
}