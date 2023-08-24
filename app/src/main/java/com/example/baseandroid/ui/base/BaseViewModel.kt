package com.example.baseandroid.ui.base

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.baseandroid.data.repository.IApiRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

abstract class BaseViewModel: ViewModel() {

    @Inject
    protected lateinit var apiRepository: IApiRepository

    val viewState = SingleLiveEvent<Int>()

    protected val compositeDisposable = CompositeDisposable()
    protected lateinit var subscription: Disposable

    override fun onCleared() {
        super.onCleared()
        if (::subscription.isInitialized) {
            compositeDisposable.clear()
            subscription.dispose()
        }
    }

    protected fun addToDisposable(disposable: Disposable) {
        compositeDisposable.remove(disposable)
        compositeDisposable.add(disposable)
    }

    protected fun onRetrieveDataStart(){
        viewState.value = View.VISIBLE
    }

    protected fun onRetrieveDataFinish(){
        viewState.value = View.GONE
    }


}