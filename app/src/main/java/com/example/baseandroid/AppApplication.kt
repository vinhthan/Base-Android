package com.example.baseandroid

import android.util.Log
import androidx.multidex.MultiDex
import com.example.baseandroid.data.sharePref.SharedPreferences
import com.example.baseandroid.di.component.AppComponent
import com.example.baseandroid.di.component.DaggerAppComponent
import com.example.baseandroid.ui.language.LanguageManager
import com.example.baseandroid.utils.Constant
import com.example.baseandroid.utils.RxBus
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class AppApplication: DaggerApplication(), HasAndroidInjector {

    lateinit var instance: AppApplication

    @Inject
    lateinit var activityInject: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate() {
        super.onCreate()
        instance = this
        MultiDex.install(this)

        RxBus.subscribe(RxBus.TAG_UPDATE_LANGUAGE_NOW, this) {
            val languageManager = LanguageManager(this)
            languageManager.updateResource(sharedPreferences.getLanguage())
            Log.d(Constant.LOG_TAG, "LanguageManager AppApplication: ${sharedPreferences.getLanguage()}")
        }

    }

    override fun androidInjector(): AndroidInjector<Any> = activityInject

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val component: AppComponent = DaggerAppComponent.builder().application(this).build()
        component.inject(this)
        return component
    }
}