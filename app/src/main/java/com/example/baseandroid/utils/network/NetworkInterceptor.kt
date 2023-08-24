package com.example.baseandroid.utils.network

import android.content.Context
import com.example.baseandroid.data.sharePref.SharedPreferences
import com.example.baseandroid.utils.isInternetAvailable
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkInterceptor @Inject constructor(private var sharedPreferences: SharedPreferences, var context: Context): Interceptor {

    private val networkEvent: NetworkEvent = NetworkEvent

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val urlEndCode = request.url.toString().replace("%2C", ",")
        val newRequest = request.newBuilder()

        if (sharedPreferences.getToken().isNotEmpty()) {
            newRequest.header("Authorization", "Bearer" +" " + sharedPreferences.getToken())
        }
        newRequest.url(urlEndCode)
        val response = chain.proceed(newRequest.build())
        //     Log.e("HEADER",request.build().headers.toString())
        if (!isInternetAvailable(context)) {
            networkEvent.publish(NetworkState.NO_INTERNET)
        } else {
            when (response.code) {
                400 -> networkEvent.publish(NetworkState.BAD_REQUEST)
                401 -> networkEvent.publish(NetworkState.UNAUTHORISED)
                403 -> networkEvent.publish(NetworkState.FORBIDDEN)
                404 -> networkEvent.publish(NetworkState.NOT_FOUND)
            }
        }

        return response

    }

}