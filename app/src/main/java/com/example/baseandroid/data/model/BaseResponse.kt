package com.example.baseandroid.data.model

data class BaseResponse<ResultType>(
    var code: Int,
    var message: String?=null,
    var data : ResultType? = null
){
    companion object{
        fun <ResultType> success(data: ResultType): BaseResponse<ResultType> =
            BaseResponse(
                0,
                message = null,
                data = data
            )
        fun <ResultType> error(message: String?): BaseResponse<ResultType> =
            BaseResponse(
                1,
                message = message,
                data = null
            )
    }
}