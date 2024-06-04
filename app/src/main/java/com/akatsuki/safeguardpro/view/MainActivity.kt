package com.akatsuki.safeguardpro.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.akatsuki.safeguardpro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // criar a toolbar
    private lateinit var appBarConfiguration: AppBarConfiguration

    // criar a navagação
    private lateinit var navController: NavController


    // Criar Binding
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // configurar binding
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // pacote de configurar a navegação e a toolbar
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.navContainer.id) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }
}