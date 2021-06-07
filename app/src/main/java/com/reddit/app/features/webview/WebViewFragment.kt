package com.reddit.app.features.webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.reddit.app.BuildConfig
import com.reddit.app.databinding.FragmentRedditWebBinding

class WebViewFragment : Fragment() {
    private var binding: FragmentRedditWebBinding? = null
    private val bind get() = binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRedditWebBinding.inflate(inflater, container, false)
        return bind?.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onStart() {
        super.onStart()
        arguments.let { bundle ->
            val arg = bundle?.let { WebViewFragmentArgs.fromBundle(it) }
            val url = BuildConfig.BASE_URL + arg?.linkUrl
            binding?.apply {
                webview.settings.javaScriptCanOpenWindowsAutomatically = true
                webview.settings.javaScriptEnabled = true
                webview.webViewClient = object: WebViewClient() {
                    override fun shouldOverrideUrlLoading(
                        view: WebView?,
                        request: WebResourceRequest?
                    ): Boolean {
                        return super.shouldOverrideUrlLoading(view, request)
                    }
                }
                webview.loadUrl(url)
            }
        }
    }
}
