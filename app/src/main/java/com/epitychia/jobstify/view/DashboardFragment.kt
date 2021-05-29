package com.epitychia.jobstify.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.epitychia.jobstify.R
import com.epitychia.jobstify.UserAdapter
import com.epitychia.jobstify.databinding.FragmentDashboardBinding
import com.epitychia.jobstify.model.User
import com.google.firebase.database.*


class DashboardFragment : Fragment() {

    private lateinit var dbref: DatabaseReference
    private lateinit var binding: FragmentDashboardBinding
    private lateinit var userArrayList: ArrayList<User>
    //    private lateinit var recyclerView: RecyclerView
    //    private lateinit var adapter: UserAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return inflater.inflate(R.layout.fragment_dashboard, container, false)
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.rvUsers.layoutManager = LinearLayoutManager(context)
        binding.rvUsers.setHasFixedSize(true)

        userArrayList = arrayListOf<User>()
        getUserData()
    }

    private fun getUserData() {

        dbref = FirebaseDatabase.getInstance().getReference("Users")

        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {

                        val user = userSnapshot.getValue(User::class.java)
                        userArrayList.add(user!!)
                    }

                    binding.rvUsers.adapter = UserAdapter(userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            DashboardFragment().apply {
                arguments = Bundle().apply {}

            }
    }
}