package com.epitychia.jobstify.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.epitychia.jobstify.HomeActivity
import com.epitychia.jobstify.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_in.*


class SignInActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        auth = FirebaseAuth.getInstance()
        btn_sign_in_login.setOnClickListener {
            val email = et_sign_in_email.text.toString().trim()
            val password = et_sign_in_password.text.toString().trim()

            if (email.isEmpty()) {
                et_sign_in_email.error = "Email is required"
                et_sign_in_email.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty() || password.length < 6) {
                et_sign_in_password.error = "Password must be at least 6 characters"
                et_sign_in_password.requestFocus()
                return@setOnClickListener
            }

            loginUser(email,password)
        }

        val btnRegister: TextView = findViewById(R.id.btn_sign_in_Register)
        btnRegister.setOnClickListener(this)

        btn_sign_in_forgot_password.setOnClickListener {
            Intent(this@SignInActivity, ResetPasswordActivity::class.java).also{
                startActivity(it)
                finish()
            }
        }

    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    Toast.makeText(this, "Login is Successful !", Toast.LENGTH_SHORT).show()
                    Intent(this, HomeActivity::class.java).also { intent ->
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }
                }else{
                    Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }




    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_sign_in_Register -> {
                val moveIntent = Intent(this, SignUpActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(moveIntent)
            }
        }
    }

}