package dev.sasikanth.nasa.apod.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import dev.sasikanth.nasa.apod.R
import dev.sasikanth.nasa.apod.databinding.ActivityMainBinding
import dev.sasikanth.nasa.apod.di.misc.injector
import dev.sasikanth.nasa.apod.di.misc.viewModels

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels { injector.mainViewModel }

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    companion object {
        private const val KEY_CURRENT_POSITION = "apod_current_position"

        var currentPosition = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt(KEY_CURRENT_POSITION, 0)
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        navController = findNavController(R.id.main_host_fragment)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_CURRENT_POSITION, currentPosition)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
