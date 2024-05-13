package com.akatsuki.safeguardpro.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.akatsuki.safeguardpro.R
import com.akatsuki.safeguardpro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Criar a toolbar
    private lateinit var appBarConfiguration: AppBarConfiguration

    //Criar a navagação
    private  lateinit var  navController: NavController


    // Criar Binding
    private var _binding : ActivityMainBinding? = null
    private val binding : ActivityMainBinding get()= _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      // configurar binding
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Pacote configura a naveção e a toolbar
        val navHostFragment = supportFragmentManager.findFragmentById(binding.navContainer.id) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }
}