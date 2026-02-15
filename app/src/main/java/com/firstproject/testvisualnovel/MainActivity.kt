package com.firstproject.testvisualnovel

import android.os.Bundle
import android.util.Log

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.firstproject.testvisualnovel.data.ParsingStore
import com.firstproject.testvisualnovel.data.Scene

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

        funTest()
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

    private fun funTest() {
        val testParsing = ParsingStore(this)
        val story = testParsing.loadStory()
        Log.d("TEST", "funTest: $story")
        Log.d("TEST", "funTest: ${story.scenes[0].id}")
        val scene: Scene? = testParsing.getSceneById(story.scenes[0].id)
        Log.d("TEST", "funTest: $scene")


    }

}