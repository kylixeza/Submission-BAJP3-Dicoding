package com.kylix.submissionbajp3.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.kylix.submissionbajp3.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    private lateinit var handler: Handler
    companion object {
        private const val TIME_FOR_SPLASH = 2000L
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        handler = Handler(mainLooper)
        rotate_loading.start()
        handler.postDelayed({
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            rotate_loading.stop()
            finish()
        }, TIME_FOR_SPLASH)
    }
}