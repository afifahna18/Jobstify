package com.epitychia.jobstify.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.epitychia.jobstify.R
import com.epitychia.jobstify.authentication.SignInActivity
import com.epitychia.jobstify.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    lateinit var profileName: TextView
    lateinit var profileLocation: TextView
    lateinit var proficiency1: TextView
    lateinit var aboutMe: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        profileName = view.findViewById(R.id.fullName) as TextView
        profileLocation = view.findViewById(R.id.Location) as TextView
        proficiency1 = view.findViewById(R.id.proficiency1) as TextView
        aboutMe = view.findViewById(R.id.AboutMe) as TextView

        val databaseReference = FirebaseDatabase.getInstance().getReference(("Profile User/")+ auth!!.uid!!)
        println("Profile User/" + auth.uid!!)
        databaseReference.addValueEventListener(object : ValueEventListener {
            @SuppressLint("SetTextI18n")
            override fun onDataChange(snapshot: DataSnapshot) {
                val userProfile = snapshot.getValue(UserModel::class.java)
                profileName.text = userProfile?.fullName
                profileLocation.text = userProfile?.location
                proficiency1.text = userProfile?.proficiency
                aboutMe.text = userProfile?.aboutMe
            }

            override fun onCancelled(error: DatabaseError) {
                activity?.let {
                    Toast.makeText(it, error.code , Toast.LENGTH_SHORT).show()
                }
            }

        })

        btn_main_log_out.setOnClickListener {
            activity?.let {
                auth.signOut()
                Intent(it, SignInActivity::class.java).also { intent ->
                    startActivity(intent)
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ProfileFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}