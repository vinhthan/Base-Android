package com.example.baseandroid.ui.language

import android.content.Context
import android.util.Log
import com.example.baseandroid.data.sharePref.SharedPreferences
import com.example.baseandroid.utils.Constant
import java.util.*

class LanguageManager(private val context: Context) {
    private val sharedPreferences = SharedPreferences(context)
    fun updateResource(code: String?) {
        val locale = Locale(code)
        Locale.setDefault(locale)
        val resources = context.resources
        val configuration = resources.configuration
        configuration.locale = locale
        resources.updateConfiguration(configuration, resources.displayMetrics)
        sharedPreferences.setLanguage(code!!)
        Log.d(Constant.LOG_TAG, "LanguageManager: $code")
    }
}