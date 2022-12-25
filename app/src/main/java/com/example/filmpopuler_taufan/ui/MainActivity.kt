package com.example.filmpopuler_taufan.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.example.filmpopuler_taufan.R
import com.example.filmpopuler_taufan.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //mendeklrasikan variable yang dibutuhkan
    //lateinit berfungsi untuk menetapkan objek view
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //menggunakan view model untuk tampilan content
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }



    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun init() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        //membuat button navigasi kembali ke halaman home fragment
        navController = navHostFragment.findNavController()
        appBarConfiguration=AppBarConfiguration(
            setOf(
                R.id.homeFragment
            )
        )

//        setupActionBarWithNavController(navController, appBarConfiguration)
    }
    //endregion
}
