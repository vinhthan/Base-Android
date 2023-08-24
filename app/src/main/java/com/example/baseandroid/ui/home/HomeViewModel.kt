package com.example.baseandroid.ui.home

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.baseandroid.data.db.DocumentReaderDao
import com.example.baseandroid.data.model.BaseResponse
import com.example.baseandroid.data.model.ItemFile
import com.example.baseandroid.data.model.User
import com.example.baseandroid.ui.base.BaseViewModel
import com.example.baseandroid.utils.Constant
import com.example.baseandroid.utils.coroutineExceptionHandler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val documentReaderDao: DocumentReaderDao): BaseViewModel() {

    fun insertAllDocumentInDevice(itemFile: ItemFile) = viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
        documentReaderDao.insertAllDocumentInDevice(itemFile)
    }

    fun getAllDocumentInDeviceByTimeDESC() = documentReaderDao.getAllDocumentInDeviceByDateDESC()


    private lateinit var disposable: Disposable
    //search
    /*private val _searchListFoodResponse = MutableLiveData<BaseResponse<DataSearchFood>>()
    val searchListFoodResponse: LiveData<BaseResponse<DataSearchFood>> get() = _searchListFoodResponse

    fun searchListFood(page: Int, size: Int, keyword: String) {
        disposable = apiRepository.getListFood(page, size, keyword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                Log.d(Constant.LOG_TAG, "searchListFood success")
                onSearch(result)
            }, { error ->
                Log.e(Constant.LOG_TAG, "searchListFood error: ${error.message}")
            })
    }

    private fun onSearch(baseResponse: BaseResponse<DataSearchFood>) {
        _searchListFoodResponse.value = baseResponse
    }*/

    private val _userListResponse = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>> get() = _userListResponse

    fun getUserList() {
        disposable = apiRepository.getListUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                Log.d(Constant.LOG_TAG, "searchListFood success: ${result}")
                onGetUserList(result)
            }, { error ->
                Log.d(Constant.LOG_TAG, "searchListFood error: ${error.message}")
            })
    }

    private fun onGetUserList(data: List<User>) {
        _userListResponse.value = data
    }

}