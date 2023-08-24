package com.example.baseandroid.ui.base

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.example.baseandroid.R
import com.example.baseandroid.data.sharePref.SharedPreferences
import com.example.baseandroid.utils.Constant
import com.example.baseandroid.utils.RxBus
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<T: ViewDataBinding, M: BaseViewModel>: DaggerFragment() {

    protected lateinit var binding: T
    protected lateinit var viewModel: M

    @Inject
    protected lateinit var sharedPreferences: SharedPreferences

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    @LayoutRes
    protected abstract fun layoutRes(): Int

    protected abstract fun viewModelClass(): Class<M>

    protected abstract fun initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes(), container, false)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this, viewModelFactory)[viewModelClass()]

        if (sharedPreferences.getSwDarkMode()) {
            setChangeColorStatusBar(R.color.background_screen_dark_mode, requireActivity())
        } else {
            setChangeColorStatusBar(R.color.background_screen_light_mode, requireActivity())
        }

        RxBus.subscribe(RxBus.TAG_CHANGE_DARK_MODE, this) {
            if (it == Constant.DARK_MODE) {
                setChangeColorStatusBar(R.color.background_screen_dark_mode, requireActivity())
            } else {
                setChangeColorStatusBar(R.color.background_screen_light_mode, requireActivity())
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        RxBus.unregister(this)
    }

    private fun setChangeColorStatusBar(color: Int, activity: Activity) {
        val window = activity.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(activity, color)
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }



}