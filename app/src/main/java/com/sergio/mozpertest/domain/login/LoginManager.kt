package com.sergio.mozpertest.domain.login

interface LoginManager {

    fun saveUserData(name: String, password: String)
    fun getUserName(): String
    fun isLogged(): Boolean
    fun logout()
}