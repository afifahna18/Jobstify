package com.epitychia.jobstify.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.epitychia.jobstify.view.HomeActivity
import com.epitychia.jobstify.R
import com.epitychia.jobstify.view.GetUserActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val btnLogin: TextView = findViewById(R.id.btn_sign_up_login)
        btnLogin.setOnClickListener(this)

        auth = FirebaseAuth.getInstance()
        btn_sign_up_register.setOnClickListener {
            val fullName = et_sign_up_full_name.text.toString().trim()
            val email = et_sign_up_email.text.toString().trim()
            val password = et_sign_up_password.text.toString().trim()

            if (fullName.isEmpty()){
                et_sign_up_full_name.error = "Full Name is required"
                et_sign_up_full_name.requestFocus()
                return@setOnClickListener
            }

            if (email.isEmpty()){
                et_sign_up_email.error = "Email is required"
                et_sign_up_email.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty() || password.length < 6){
                et_sign_up_password.error = "Password must be at least 6 characters"
                et_sign_up_password.requestFocus()
                return@setOnClickListener
            }

            registerUser(email, password)
        }

    }

    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                    if (it.isSuccessful){
                        Toast.makeText(this, "Registration is Successful !", Toast.LENGTH_SHORT).show()
                        Intent(this, GetUserActivity::class.java).also { intent ->
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                        }
                    }
                    else{
                        Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }
    }


    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_sign_up_login -> {
                val moveIntent = Intent(this, SignInActivity::class.java)
                startActivity(moveIntent)
                finish()
            }
        }
    }
}