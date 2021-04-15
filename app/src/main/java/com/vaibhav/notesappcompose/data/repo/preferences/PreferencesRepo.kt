package com.vaibhav.notesappcompose.data.repo.preferences

import android.content.SharedPreferences
import com.google.gson.Gson
import com.vaibhav.notesappcompose.data.models.entity.User
import javax.inject.Inject

class PreferencesRepo @Inject constructor(private val sharedPref: SharedPreferences) {

    companion object {
        const val USER_SAVE_KEY = "userSavedData"
    }


    fun isUserLoggedIn() = getUserData() != null

    fun getUserData(): User? {
        val data = sharedPref.getString(USER_SAVE_KEY, null)
        return data?.let {
            Gson().fromJson(data, User::class.java)
        }
    }

    fun saveUserData(user: User) {
        val data = Gson().toJson(user)
        sharedPref.edit().putString(USER_SAVE_KEY, data).apply()
    }


}