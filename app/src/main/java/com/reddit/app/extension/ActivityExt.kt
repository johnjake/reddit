package com.reddit.app.extension

import android.app.Activity
import android.view.View
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.reddit.app.R

fun Activity.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Activity?.hideNavigation() {
    val bottomNavigationView = this?.findViewById<BottomNavigationView>(R.id.bottomNavigation)
    bottomNavigationView?.visibility = View.GONE
}

fun Activity?.showNavigation() {
    val bottomNavigationView = this?.findViewById<BottomNavigationView>(R.id.bottomNavigation)
    bottomNavigationView?.visibility = View.VISIBLE
}
