package com.example.jobfinder

import android.annotation.SuppressLint
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.jobfinder.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Toasty.Config.getInstance().setGravity(Gravity.TOP).apply()
        auth = Firebase.auth

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
        if(auth.currentUser == null) {
            navGraph.setStartDestination(R.id.logInFragment2)
            navController.graph = navGraph
        }
        navView.setupWithNavController(navController)

//        if(navController.currentDestination != setOf(R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)) {
//            binding.navView.visibility = View.GONE
//        }

        //Set visibility for bottom navigation
        navController.addOnDestinationChangedListener { _: NavController?, navDestination: NavDestination, _: Bundle? ->
            when(navDestination.id) {
                R.id.navigation_home, R.id.navigation_notifications -> binding.navView.visibility = View.VISIBLE
                else -> binding.navView.visibility = View.GONE
            }
        }
    }
}