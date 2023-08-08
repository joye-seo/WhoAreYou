package com.example.whoareyou

import android.content.Context
import android.content.SharedPreferences

class PreferenceUtil(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("pref_file", Context.MODE_PRIVATE)

    // 저장된 리스트를 불러오는 함수
    fun getString(): List<String> {
        val saveSet = prefs.getStringSet("signUpData", HashSet()) ?: HashSet()
        return saveSet.toList()
    }

    // 저장된 리스트를 저장하는 함수
    fun setString(id: String, password: String, name: String, age: String, mbti: String) {

        // 데이터를 아래와 같은 형태만들어서 mutableList로 저장함
        val signUpData = "$id : $password : $name : $age : $mbti"
        val signUpDataList = getString().toMutableSet()
        signUpDataList.add(signUpData)
        prefs.edit().putStringSet("signUpData", signUpDataList).apply()
    }

}

