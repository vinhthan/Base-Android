package com.example.baseandroid.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop
import kotlinx.coroutines.CoroutineExceptionHandler
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
    throwable.printStackTrace()
}

fun Double.decimalOneCommas(): String {
    val symbols = DecimalFormatSymbols(Locale.US)
    symbols.decimalSeparator = '.'
    symbols.groupingSeparator = ','
    val decimalFormat = DecimalFormat("#,###.##")
    decimalFormat.maximumFractionDigits = 1
    decimalFormat.decimalFormatSymbols = symbols
    decimalFormat.isGroupingUsed = true
    return decimalFormat.format(this)
}

fun setSpannableTextColor(context: Context, text: String, start: Int, end: Int, color: Int): Spannable {
    val calories = SpannableString(text)
    calories.setSpan(RelativeSizeSpan(1.0f), start, end, 0) // set size
    calories.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, color)), start, end, 0) // set color
    /*val typeface: Typeface? = ResourcesCompat.getFont(requireContext(), R.font.nunito_bold)
    calories.setSpan(StyleSpan(typeface!!.style), 0, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)*/
    return calories
}

fun View.setMargins(
    left: Int = this.marginLeft,
    top: Int = this.marginTop,
    right: Int = this.marginRight,
    bottom: Int = this.marginBottom
) {

    layoutParams = (layoutParams as ViewGroup.MarginLayoutParams).apply {
        setMargins(left, top, right, bottom)
    }
}

@SuppressLint("SimpleDateFormat")
fun setSimpleDateFormat(time: Long): String {
    val simpleDate = SimpleDateFormat("HH:mm, MMM dd, yyyy")
    return simpleDate.format(time)
}

fun isInternetAvailable(context: Context): Boolean {
    val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val connection = manager.activeNetworkInfo
    return connection != null && connection.isConnectedOrConnecting
}