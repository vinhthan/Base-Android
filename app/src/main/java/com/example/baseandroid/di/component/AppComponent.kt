package com.example.baseandroid.di.component

import android.app.Application
import com.example.baseandroid.AppApplication
import com.example.baseandroid.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ActivityModule::class, AppModule::class, DataBaseModule::class,
    FragmentModule::class, ViewModelModule::class, NetworkModule::class])
interface AppComponent: AndroidInjector<AppApplication> {

    override fun inject(instance: AppApplication?) {

    }

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }



}