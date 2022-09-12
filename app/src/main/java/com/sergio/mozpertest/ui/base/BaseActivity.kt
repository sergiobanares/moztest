package com.sergio.mozpertest.ui.base

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.sergio.mozpertest.domain.Resource

internal abstract class BaseActivity<B : ViewBinding> : AppCompatActivity() {

    protected var bundle: Bundle? = null
    protected lateinit var binding: B
    private lateinit var loadingView: LoadingView

    abstract fun getViewBinding(): B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        val contentView: ViewGroup = window.decorView.findViewById(android.R.id.content)
        loadingView = LoadingView.create(contentView, this)
    }

    protected fun <T : Any> viewStateObserverOf(
        onSuccessAction: (T?) -> Unit,
        retryAction: () -> Unit
    ) = Observer<Resource<T>> { viewState ->
        loadingView.dismiss()
        when (viewState) {
            is Resource.Fail -> showError(viewState.message, retryAction)
            is Resource.Loading -> loadingView.show()
            is Resource.Success -> onSuccessAction(viewState.data)
            else -> showError("Unknown Error", retryAction)
        }
    }

    private fun showError(message: String?, retryAction: () -> Unit) {
        //TODO: Load Error Screen With Retry Button
        Toast.makeText(
            this,
            "Error: $message",
            Toast.LENGTH_LONG
        ).show()
    }

    protected fun navigateTo(context: Context, deepLink: String, bundle: Bundle? = null) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(deepLink))
        ContextCompat.startActivity(context, intent, bundle)
    }

    protected fun getParameter(intent: Intent, parameter: String): String {
        return intent.data?.getQueryParameter(parameter) ?: EMPTY
    }

    private companion object {
        const val EMPTY = ""
    }
}
