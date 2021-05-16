package com.epitychia.jobstify.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.epitychia.jobstify.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_email_sent.*
import kotlinx.android.synthetic.main.activity_email_sent.btn_reset_password_send_email
import kotlinx.android.synthetic.main.activity_reset_password.*

class EmailSentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_sent)

        btn_reset_password_send_email.setOnClickListener {
            Intent(this,SignInActivity::class.java).also{
                startActivity(it)
                finish()
            }
        }

    }
}