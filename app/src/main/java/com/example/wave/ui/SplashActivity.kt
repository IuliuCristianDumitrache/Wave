package com.example.wave.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wave.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint

//TODO - Improvement to use the new splashScreen API
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    lateinit var views: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        views = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(views.root)

        views.animationView.animate().setDuration(2000).withEndAction { openMainActivity() }.start()
    }

    private fun openMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}