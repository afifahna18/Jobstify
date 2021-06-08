package com.epitychia.jobstify.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.epitychia.jobstify.R
import com.epitychia.jobstify.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import java.util.*


class GetUserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var etFullName: EditText
    private lateinit var etLocation: EditText
    private lateinit var etProficiency1: EditText
    private lateinit var etAboutMe: EditText
    private lateinit var btnSave: Button
    private lateinit var image: String
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_edit_profile)
        etFullName = findViewById(R.id.til_full_name)
        etLocation = findViewById(R.id.til_location)
        etProficiency1 = findViewById(R.id.til_proficiency1)
        etAboutMe = findViewById(R.id.til_aboutMe)
        btnSave = findViewById(R.id.Btn_done)
        btnSave.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        saveData()
    }

    private fun saveData(){
        val fullName = etFullName.text.toString().trim()
        val location = etLocation.text.toString().trim()
        val proficiency1 = etProficiency1.text.toString().trim()
        val aboutMe = etAboutMe.text.toString().trim()

        if (fullName.isEmpty()){
            etFullName.error = "ISI NAMA"
            return
        }
        if (location.isEmpty()){
            etLocation.error = "ISI LOCATION"
            return
        }
        if (proficiency1.isEmpty()){
            etProficiency1.error = "ISI PROFICIENCY"
            return
        }

        val ref = FirebaseDatabase.getInstance().getReference("Profile User")
        val userId = ref.push().key

        val profileUser = UserModel(fullName, location, proficiency1, aboutMe)

        if (userId != null) {
            ref.child(userId).setValue(profileUser).addOnCompleteListener {
                Toast.makeText(applicationContext, "DATA ADDED SUCCESSFULLY", Toast.LENGTH_SHORT).show()
            }
        }

        Intent(this@GetUserActivity, HomeActivity::class.java).also{
            startActivity(it)
            finish()}
    }


}











