package com.epitychia.jobstify.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.epitychia.jobstify.JobViewModel
import com.epitychia.jobstify.adapter.SearchAdapter
import com.epitychia.jobstify.databinding.ActivitySearchBinding
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding

    private val searchAdapter = SearchAdapter()
    private val dbRef = FirebaseDatabase.getInstance().getReference("Users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[JobViewModel::class.java]
        val listJobs = viewModel.getJobs()

        searchAdapter.setData(listJobs)

        binding.rvJobs.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = searchAdapter
            getJobData("")
        }
        search()
    }

    private fun search() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let { getJobData(it) }
            }

            override fun afterTextChanged(s: Editable?) {
                getJobData(s.toString())
            }
        })
    }

    private fun getJobData(search: CharSequence) {
        searchAdapter.filter.filter(search)
    }


//    private fun searchJob() {
//        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
//        val searchView = binding.etSearch as SearchView
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                Toast.makeText(this@SearchActivity, query, Toast.LENGTH_SHORT).show()
//                return true
//
////                getJobData(query.toString())
////                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
////                getJobData(newText.toString())
////                return true
//                return false
//            }
//        })
//    }

//
//    private fun getUserData(search: String) {
//
//        userArrayList.clear()
//
//        dbRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//
//                if (snapshot.exists()) {
//
//                    if (search != "") {
//                        for (userSnapshot in snapshot.children) {
//                            val user = userSnapshot.getValue(User::class.java)
//                            if (user != null) {
//                                if (user.name!!.contains(search)) {
//                                    userArrayList.add(user)
//                                }
//                            }
//                        }
//                    } else {
//                        for (userSnapshot in snapshot.children) {
//                            val user = userSnapshot.getValue(User::class.java)
//                            userArrayList.add(user!!)
//                        }
//                    }
//                    Log.d("Size", userArrayList.size.toString())
//                    adapter.setUsers(userArrayList)
//                    adapter.notifyDataSetChanged()
//                    binding.rvJobs.adapter = adapter
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {}
//
//        })
//    }
}