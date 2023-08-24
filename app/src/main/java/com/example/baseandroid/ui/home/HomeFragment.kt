package com.example.baseandroid.ui.home

import android.util.Log
import com.example.baseandroid.R
import com.example.baseandroid.databinding.FragmentHomeBinding
import com.example.baseandroid.ui.base.BaseFragment
import com.example.baseandroid.utils.Constant

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun layoutRes(): Int = R.layout.fragment_home

    override fun viewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun initView() {
        viewModel.getUserList()

        viewModel.userList.observe(this) {
            Log.d(Constant.LOG_TAG, "userList: $it")
        }

    }


}