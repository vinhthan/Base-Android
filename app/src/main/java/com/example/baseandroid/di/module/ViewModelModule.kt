package com.example.baseandroid.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.baseandroid.di.util.ViewModelFactory
import com.example.baseandroid.di.util.ViewModelKey
import com.example.baseandroid.ui.home.HomeViewModel
import com.example.baseandroid.ui.main.MainViewModel
import com.example.baseandroid.ui.recent.RecentViewModel
import com.example.baseandroid.ui.setting.SettingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RecentViewModel::class)
    internal abstract fun bindRecentViewModel(recentViewModel: RecentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingViewModel::class)
    internal abstract fun bindSettingViewModel(settingViewModel: SettingViewModel): ViewModel

}