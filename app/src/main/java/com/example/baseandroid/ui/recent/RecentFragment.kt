package com.example.baseandroid.ui.recent

import com.example.baseandroid.R
import com.example.baseandroid.databinding.FragmentRecentBinding
import com.example.baseandroid.ui.base.BaseFragment

class RecentFragment : BaseFragment<FragmentRecentBinding, RecentViewModel>() {

    companion object {
        fun newInstance() = RecentFragment()
    }

    override fun layoutRes(): Int = R.layout.fragment_recent

    override fun viewModelClass(): Class<RecentViewModel> = RecentViewModel::class.java

    override fun initView() {

    }


}