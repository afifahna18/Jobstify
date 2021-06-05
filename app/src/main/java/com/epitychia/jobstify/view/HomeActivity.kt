package com.epitychia.jobstify.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.epitychia.jobstify.R
import com.epitychia.jobstify.databinding.ActivityHomeBinding
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_profile.*


class HomeActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_home)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addFragment(DashboardJobFragment.newInstance())
        bottomNavigation.show(0)
        bottomNavigation.add(MeowBottomNavigation.Model(0, R.drawable.ic_home))
        bottomNavigation.add(MeowBottomNavigation.Model(1, R.drawable.ic_favorite))
        bottomNavigation.add(MeowBottomNavigation.Model(2, R.drawable.ic_message))
        bottomNavigation.add(MeowBottomNavigation.Model(3, R.drawable.ic_profile))

        bottomNavigation.setOnClickMenuListener {
            when (it.id){
                0 -> {
                    replaceFragment(DashboardJobFragment.newInstance())
                }
                1 -> {
                    replaceFragment(FavouriteFragment.newInstance())
                }
                2 -> {
                    replaceFragment(ChatroomFragment.newInstance())
                }
                3 -> {
                    replaceFragment(ProfileFragment.newInstance())
                } else -> {
                replaceFragment(DashboardJobFragment.newInstance())
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransition = supportFragmentManager.beginTransaction()
            fragmentTransition.replace(R.id.fragmentContainer,fragment).addToBackStack(Fragment::class.java.simpleName).commit()
    }

    private fun addFragment(fragment: Fragment){
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.add(R.id.fragmentContainer,fragment).addToBackStack(Fragment::class.java.simpleName).commit()
    }
}









