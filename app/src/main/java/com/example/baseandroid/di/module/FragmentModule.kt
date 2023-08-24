package com.example.baseandroid.di.module

import com.example.baseandroid.ui.home.HomeFragment
import com.example.baseandroid.ui.recent.RecentFragment
import com.example.baseandroid.ui.setting.SettingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    internal abstract fun bindHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    internal abstract fun bindRecentFragment(): RecentFragment

    @ContributesAndroidInjector
    internal abstract fun bindSettingFragment(): SettingFragment


}