package com.example.baseandroid.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.example.baseandroid.R
import com.example.baseandroid.databinding.ActivityMainBinding
import com.example.baseandroid.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    companion object {
        fun startActivity(activity: Activity, bundle: Bundle? = null) {
            val intent = Intent(activity, MainActivity::class.java)
            if (bundle != null) {
                intent.putExtras(bundle)
            }
            activity.startActivity(intent)
        }
    }

    override fun layoutRes(): Int = R.layout.activity_main

    override fun viewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun handleViewState() {

    }

    override fun initView() {
        overridePendingTransition(R.anim.right_in, R.anim.left_out)

        setupBottomNavigation()

    }

    private fun setupBottomNavigation() {
        binding.bottomNavigationView.selectedItemId = R.id.menu_home
        val mAdapter = MainAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = mAdapter
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when(position) {
                    0 -> binding.bottomNavigationView.selectedItemId = R.id.menu_home
                    1 -> binding.bottomNavigationView.selectedItemId = R.id.menu_recent
                    else -> binding.bottomNavigationView.selectedItemId = R.id.menu_settings
                }
            }
        })

        binding.bottomNavigationView.setOnItemSelectedListener { menuId ->
            when(menuId.itemId) {
                R.id.menu_home -> {
                    binding.viewPager.currentItem = 0
                    true
                }
                R.id.menu_recent -> {
                    binding.viewPager.currentItem = 1
                    true
                }
                else -> {
                    binding.viewPager.currentItem = 2
                    true
                }
            }
        }

        binding.viewPager.offscreenPageLimit = 2
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.left_in, R.anim.right_out)
    }

}