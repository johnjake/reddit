package com.reddit.app.features.main

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.reddit.app.R
import com.reddit.app.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.MutableStateFlow

class RedditMainActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigationMenu()
    }

    private fun setupNavigationMenu(){
        bottomNavView = binding.bottomNavigation.apply {
            itemIconTintList = null
        }
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavView.setOnNavigationItemSelectedListener(object :
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(@NonNull item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.feeds_stack -> {
                        navController.navigate(R.id.feedsFragment)
                        return true
                    }
                    R.id.car_stack -> {
                        navController.navigate(R.id.subRedditFragment)
                        return true
                    }
                    /* R.id.inbox_stack -> {
                         navController.navigate(R.id.inboxFragment)
                         return true
                     }
                     R.id.profile_stack -> {
                         navController.navigate(R.id.movieFragment)
                         // CarDialog.builderAlert(binding.root.context, "Under Construction", "Sorry this page is under-construction")
                         return true
                     } */
                }
                return true
            }
        })
    }

    companion object {
        val onBackPress = MutableStateFlow(false)
    }
}
