package com.example.baseandroid.ui.setting

import com.example.baseandroid.R
import com.example.baseandroid.databinding.FragmentSettingBinding
import com.example.baseandroid.ui.base.BaseFragment

class SettingFragment : BaseFragment<FragmentSettingBinding, SettingViewModel>() {

    companion object {
        fun newInstance() = SettingFragment()
    }

    override fun layoutRes(): Int = R.layout.fragment_setting

    override fun viewModelClass(): Class<SettingViewModel> = SettingViewModel::class.java

    override fun initView() {

    }


}