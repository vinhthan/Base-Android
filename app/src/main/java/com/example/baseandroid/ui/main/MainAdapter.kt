package com.example.baseandroid.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.baseandroid.ui.home.HomeFragment
import com.example.baseandroid.ui.recent.RecentFragment
import com.example.baseandroid.ui.setting.SettingFragment

class MainAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> HomeFragment.newInstance()
            1 -> RecentFragment.newInstance()
            else -> SettingFragment.newInstance()
        }
    }
}