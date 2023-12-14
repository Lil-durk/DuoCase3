package com.example.duocase3procyclingapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.duocase3procyclingapp.fragments.HomeFragment
import com.example.duocase3procyclingapp.fragments.DashBoardFragment
import com.example.duocase3procyclingapp.fragments.NotificationsFragment
import com.example.duocase3procyclingapp.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.navigation_view)
        navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> loadFragment(HomeFragment())
                R.id.navigation_dashboard -> loadFragment(DashBoardFragment())
                R.id.navigation_notifications -> loadFragment(NotificationsFragment())
                R.id.navigation_profile -> loadFragment(ProfileFragment())
            }
            true
        }

        // Load the default fragment
        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}
