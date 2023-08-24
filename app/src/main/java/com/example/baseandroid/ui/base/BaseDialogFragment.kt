package com.example.baseandroid.ui.base

import android.content.res.Resources
import android.os.Bundle
import android.view.*
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.baseandroid.R
import com.example.baseandroid.utils.RxBus
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseDialogFragment<T: ViewDataBinding, M: BaseViewModel> : DialogFragment() {

    protected lateinit var binding: T
    protected lateinit var viewModel: M

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    @LayoutRes
    protected abstract fun layoutRes(): Int

    protected abstract fun viewModelClass(): Class<M>

    protected abstract fun handleViewState(viewState: Int)

    protected abstract fun initView()

    protected abstract fun getDataFromBundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().window.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.bg_dialog))
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
        getDataFromBundle()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    fun setBackgroundDialog(drawable: Int) {
        if (dialog != null && dialog!!.window != null) {
            dialog!!.window!!.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), drawable))
            dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        }
    }

    fun setWith(value : Int){
        val screenWith = Resources.getSystem().displayMetrics.widthPixels
        val params = dialog!!.window!!.attributes
        params.width =screenWith- screenWith/value
        params.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes = params
    }
    fun fullScreenDialog(){
        //    val screenWith = Resources.getSystem().displayMetrics.widthPixels
        val params = dialog!!.window!!.attributes
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        params.height = WindowManager.LayoutParams.MATCH_PARENT
        dialog!!.window!!.attributes = params
    }

    override fun onDestroy() {
        super.onDestroy()
        RxBus.unregister(this)
    }


}