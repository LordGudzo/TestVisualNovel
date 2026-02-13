package com.firstproject.testvisualnovel

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.firstproject.testvisualnovel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("Binding must not be null")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBackground.setImageDrawable(
            ContextCompat.getDrawable(
                this@MainActivity,
                R.drawable.bg_test_image
            )
        )



        binding.btnHeaderMenu.setOnClickListener {
            binding.menuField.isVisible = true
        }

        binding.btnMenuFieldContinue.setOnClickListener {
            binding.menuField.isVisible =false
        }

    }
}