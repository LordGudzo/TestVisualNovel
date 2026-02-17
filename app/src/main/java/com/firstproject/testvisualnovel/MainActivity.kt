package com.firstproject.testvisualnovel

import android.os.Bundle
import android.widget.Button

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.firstproject.testvisualnovel.data.Choice
import com.firstproject.testvisualnovel.data.ParsingStore
import com.firstproject.testvisualnovel.data.SceneLogical

import com.firstproject.testvisualnovel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("Binding must not be null")

    private lateinit var logical: SceneLogical

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val parsingStore = ParsingStore(this)
        logical = SceneLogical(parsingStore)

        setClickerForHeader()
        showScene()




    }

    private fun showScene() {
        val imagePath: String = logical.backgroundImagePath

        //set background picture
        Glide.with(this)
            .load("file:///android_asset/${imagePath}")
            .into(binding.ivBackground)

        binding.tvStory.text = logical.currentSceneText

        createChooseButtons()
    }

    private fun createChooseButtons() {
        val choices: List<Choice> = logical.choices

        for (i in choices){
            val button: Button = Button(this).apply {
                text = i.text
                setOnClickListener {
                    binding.llStoryField.removeAllViews()
                    if (logical.setNewScene(i.nextScene)) {
                        showScene()
                    }
                }
            }
            binding.llStoryField.addView(button)
        }
    }

    private fun setClickerForHeader() {
        binding.btnHeaderMenu.setOnClickListener {
            binding.menuField.isVisible = true
            binding.constraintStoryField.isVisible = false
        }

        binding.btnMenuFieldContinue.setOnClickListener {
            binding.menuField.isVisible =false
            binding.constraintStoryField.isVisible = true
        }
    }
}