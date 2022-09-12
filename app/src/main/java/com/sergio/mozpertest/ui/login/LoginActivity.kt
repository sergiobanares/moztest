package com.sergio.mozpertest.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sergio.mozpertest.databinding.ActivityLoginBinding
import com.sergio.mozpertest.domain.login.LoginManager
import com.sergio.mozpertest.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    @Inject
    lateinit var loginManager: LoginManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            mozperLoginButton.setOnClickListener {
                loginManager.saveUserData(
                    mozperLoginUsername.text.toString(),
                    mozperLoginPassword.text.toString()
                )
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            }
        }
    }
}