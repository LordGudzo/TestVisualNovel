package com.firstproject.testvisualnovel

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.Button

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.firstproject.testvisualnovel.data.Choice
import com.firstproject.testvisualnovel.data.ParsingStore
import com.firstproject.testvisualnovel.data.SceneLogical

import com.firstproject.testvisualnovel.databinding.ActivityMainBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("Binding must not be null")

    private lateinit var logical: SceneLogical

    private var typingJob: Job? = null

    private var mediaPlayer: MediaPlayer? = null
    private var isMuted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("TEST", "start")
        val parsingStore = ParsingStore(this)

        Log.d("TEST", "parsingStore $parsingStore")
        logical = SceneLogical(parsingStore)
        Log.d("TEST", "logical $logical")

        setClickerForHeader()
        setMusic()
        showScene()


    }
    private fun animateText(fullText: String) {
        typingJob?.cancel() // если уже печатается — остановить

        typingJob = lifecycleScope.launch {
            binding.tvStory.text = ""

            for (char in fullText) {
                binding.tvStory.append(char.toString())
                delay(20) // скорость печати (мс)
            }

            createChooseButtons()
        }
    }

    private fun setMusic() {
        mediaPlayer = MediaPlayer.create(this, R.raw.bg_music)
        mediaPlayer?.isLooping = true
        mediaPlayer?.start()
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.pause()
    }

    override fun onResume() {
        super.onResume()
        if (!isMuted) {
            mediaPlayer?.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    private fun showScene() {
        val imagePath: String = logical.backgroundImagePath

        //set background picture
        Glide.with(this)
            .load("file:///android_asset/${imagePath}")
            .into(binding.ivBackground)

        animateText(logical.currentSceneText)


    }

    private fun createChooseButtons() {
        val choices: List<Choice> = logical.choices
        for (i in choices){
            Log.d("TEST", "choice $i")
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

        binding.btnHeaderVolumeFree.setOnClickListener {
            mediaPlayer?.pause()
            binding.btnHeaderVolumeFree.isVisible = false
            binding.btnHeaderVolumeMute.isVisible = true
        }

        binding.btnHeaderVolumeMute.setOnClickListener {
            mediaPlayer?.start()
            binding.btnHeaderVolumeFree.isVisible = true
            binding.btnHeaderVolumeMute.isVisible = false
        }
    }
}