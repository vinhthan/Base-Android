package com.example.baseandroid.data.sharePref

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferences @Inject constructor(private val context: Context) {

    private val USER_TOKEN = "user_token"
    private val FIRST_RUN_APP = "FIRST_RUN_APP"
    private val TYPE_DISPLAY_HOME = "TYPE_DISPLAY_HOME"
    private val LANGUAGE = "LANGUAGE"
    private val SWITCH_DARK_MODE_ON_OFF = "SWITCH_DARK_MODE_ON_OFF"
    private val SORT_BY_LAST_MODIFIED = "SORT_BY_LAST_MODIFIED"
    private val SORT_BY_NAME = "SORT_BY_NAME"
    private val SORT_BY_FILE_SIZE = "SORT_BY_FILE_SIZE"
    private val SORT_BY_FROM_NEW_TO_OLD = "SORT_BY_FROM_NEW_TO_OLD"
    private val SORT_BY_FROM_OLD_TO_NEW = "SORT_BY_FROM_OLD_TO_NEW"


    private fun getPref(context: Context): SharedPreferences {
        //  return PreferenceManager.getDefaultSharedPreferences(context);
        return context.getSharedPreferences(
            context.packageName,
            Context.MODE_PRIVATE
        )
    }

    fun setToken(token: String) {
        val editor: SharedPreferences.Editor = getPref(context)
            .edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun getToken(): String {
        return getPref(context).getString(
            USER_TOKEN, ""
        )!!
    }

    fun setFirstRun(isFirst: Boolean) {
        val editor: SharedPreferences.Editor = getPref(context).edit()
        editor.putBoolean(FIRST_RUN_APP, isFirst)
        editor.apply()
    }

    fun getFirstRun(): Boolean {
        return getPref(context).getBoolean(
            FIRST_RUN_APP, true
        )
    }

    fun setTypeDisplayPref(typeDisplay: Int) {
        val editor: SharedPreferences.Editor = getPref(context).edit()
        editor.putInt(TYPE_DISPLAY_HOME, typeDisplay)
        editor.apply()
    }

    fun getTypeDisplayPref(): Int {
        return getPref(context).getInt(
            TYPE_DISPLAY_HOME, 1
        )
    }

    fun setLanguage(token: String) {
        val editor: SharedPreferences.Editor = getPref(context).edit()
        editor.putString(LANGUAGE, token)
        editor.apply()
    }

    fun getLanguage(): String {
        return getPref(context).getString(
            LANGUAGE, ""
        )!!
    }

    fun setSwDarkMode(isFirst: Boolean) {
        val editor: SharedPreferences.Editor = getPref(context).edit()
        editor.putBoolean(SWITCH_DARK_MODE_ON_OFF, isFirst)
        editor.apply()
    }

    fun getSwDarkMode(): Boolean {
        return getPref(context).getBoolean(
            SWITCH_DARK_MODE_ON_OFF, false
        )
    }

    fun setSortByLastModified(isFirst: Boolean) {
        val editor: SharedPreferences.Editor = getPref(context).edit()
        editor.putBoolean(SORT_BY_LAST_MODIFIED, isFirst)
        editor.apply()
    }

    fun getSortByLastModified(): Boolean {
        return getPref(context).getBoolean(
            SORT_BY_LAST_MODIFIED, true
        )
    }

    fun setSortByName(isFirst: Boolean) {
        val editor: SharedPreferences.Editor = getPref(context).edit()
        editor.putBoolean(SORT_BY_NAME, isFirst)
        editor.apply()
    }

    fun getSortByName(): Boolean {
        return getPref(context).getBoolean(
            SORT_BY_NAME, false
        )
    }

    fun setSortByFileSize(isFirst: Boolean) {
        val editor: SharedPreferences.Editor = getPref(context).edit()
        editor.putBoolean(SORT_BY_FILE_SIZE, isFirst)
        editor.apply()
    }

    fun getSortByFileSize(): Boolean {
        return getPref(context).getBoolean(
            SORT_BY_FILE_SIZE, false
        )
    }

    fun setSortByFromNewToOld(isFirst: Boolean) {
        val editor: SharedPreferences.Editor = getPref(context).edit()
        editor.putBoolean(SORT_BY_FROM_NEW_TO_OLD, isFirst)
        editor.apply()
    }

    fun getSortByFromNewToOld(): Boolean {
        return getPref(context).getBoolean(
            SORT_BY_FROM_NEW_TO_OLD, true
        )
    }

    fun setSortByFromOldToNew(isFirst: Boolean) {
        val editor: SharedPreferences.Editor = getPref(context).edit()
        editor.putBoolean(SORT_BY_FROM_OLD_TO_NEW, isFirst)
        editor.apply()
    }

    fun getSortByLFromOldToNew(): Boolean {
        return getPref(context).getBoolean(
            SORT_BY_FROM_OLD_TO_NEW, false
        )
    }

}