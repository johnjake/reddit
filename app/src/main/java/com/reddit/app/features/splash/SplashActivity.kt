package com.reddit.app.features.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.reddit.app.databinding.ActivitySplashBinding
import com.reddit.app.features.main.RedditMainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    private val scope = CoroutineScope(Dispatchers.IO)
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        scope.launch {
            delay(5000)
            launchActivity()
        }
    }

    private fun launchActivity() {
        startActivity(Intent(this, RedditMainActivity::class.java).apply {
            putExtra(internet, isOnline)
        })
    }

    companion object {
        const val internet = "Internet"
        const val isOnline = 1
    }
}
