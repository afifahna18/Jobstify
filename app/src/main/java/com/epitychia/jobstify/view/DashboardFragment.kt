package com.epitychia.jobstify.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.epitychia.jobstify.adapter.UserAdapter
import com.epitychia.jobstify.databinding.FragmentDashboardBinding
import com.epitychia.jobstify.model.User
import com.google.firebase.database.*


class DashboardFragment : Fragment() {

    private lateinit var dbRef: DatabaseReference
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


        binding.etSearch.setOnClickListener{
            val moveIntent = Intent(context, SearchActivity::class.java)
            context?.startActivity(moveIntent)
        }

        binding.rvUsers.layoutManager = LinearLayoutManager(context)
        binding.rvUsers.setHasFixedSize(true)

        userArrayList = arrayListOf<User>()
        getUserData()
    }

    private fun getUserData() {

        dbRef = FirebaseDatabase.getInstance().getReference("Users")

        dbRef.addValueEventListener(object : ValueEventListener {
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