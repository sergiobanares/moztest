package com.sergio.mozpertest.domain.login

import android.content.SharedPreferences
import javax.inject.Inject


class LoginManagerImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : LoginManager {

    override fun saveUserData(name: String, password: String) {
        sharedPreferences.edit()
            .putString(USER_NAME, name)
            .putString(USER_PASSWORD, password)
            .apply()
    }


    override fun getUserName(): String {
        return sharedPreferences.getString(
            USER_NAME, "UserName"
        )!! // This should be checked
    }

    override fun isLogged() = sharedPreferences.contains(USER_NAME)

    override fun logout() {
        sharedPreferences.edit().clear().apply()
    }

    private companion object {
        const val USER_NAME = "user_name"
        const val USER_PASSWORD = "user_password"
    }
}
