package com.example.baseandroid.ui.base

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.example.baseandroid.R
import com.example.baseandroid.data.sharePref.SharedPreferences
import com.example.baseandroid.ui.language.LanguageManager
import com.example.baseandroid.utils.Constant
import com.example.baseandroid.utils.RxBus
import com.example.baseandroid.utils.network.NetworkEvent
import com.example.baseandroid.utils.network.NetworkState
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.functions.Consumer
import javax.inject.Inject

abstract class BaseActivity<T: ViewDataBinding, M: BaseViewModel>: DaggerAppCompatActivity() {

    protected lateinit var binding: T
    protected lateinit var viewModel: M

    @Inject
    protected lateinit var sharedPreferences: SharedPreferences

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    @LayoutRes
    protected abstract fun layoutRes(): Int

    protected abstract fun viewModelClass(): Class<M>

    protected abstract fun handleViewState()

    protected abstract fun initView()

    var saveInstanceState: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.saveInstanceState = saveInstanceState
        binding = DataBindingUtil.setContentView(this, layoutRes())
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this, viewModelFactory)[viewModelClass()]

        initView()

        RxBus.subscribe(RxBus.TAG_UPDATE_LANGUAGE_NOW, this) {
            val languageManager = LanguageManager(this)
            languageManager.updateResource(sharedPreferences.getLanguage())
            recreate()
        }

        if (sharedPreferences.getSwDarkMode()) {
            setChangeColorStatusBar(R.color.background_screen_dark_mode, this)
        } else {
            setChangeColorStatusBar(R.color.background_screen_light_mode, this)
        }

        RxBus.subscribe(RxBus.TAG_CHANGE_DARK_MODE, this) {
            if (it == Constant.DARK_MODE) {
                setChangeColorStatusBar(R.color.background_screen_dark_mode, this)
            } else {
                setChangeColorStatusBar(R.color.background_screen_light_mode, this)
            }
        }


        val languageManager = LanguageManager(this)
        languageManager.updateResource(sharedPreferences.getLanguage())


        viewModel.viewState
        RxBus.subscribe(RxBus.SHOW_POPUP_NOT_INTERNET, this){
            this.runOnUiThread {
                //showPopupConnectionInternet()
            }
        }

    }

    private fun setChangeColorStatusBar(color: Int, activity: Activity) {
        val window = activity.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(activity, color)
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }

    override fun onResume() {
        super.onResume()
        NetworkEvent.register(this, Consumer {
            when(it) {
                NetworkState.NO_INTERNET -> Toast.makeText(this, "No internet", Toast.LENGTH_SHORT).show()
                else -> {}
            }
        })
    }

    override fun onStop() {
        super.onStop()
        NetworkEvent.unregister(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        RxBus.unregister(this)
    }

    /*fun showPopupConnectionInternet(){
        popupTimeOut?.let {
            if(!it.isShowing){
                it.show()
            }
        }
    }*/






}