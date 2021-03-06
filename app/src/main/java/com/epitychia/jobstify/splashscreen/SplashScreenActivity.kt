package com.epitychia.jobstify.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.epitychia.jobstify.R
import com.epitychia.jobstify.intro.IntroActivity

class SplashScreenActivity : AppCompatActivity() {

    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this, IntroActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }, 3000)
    }
}