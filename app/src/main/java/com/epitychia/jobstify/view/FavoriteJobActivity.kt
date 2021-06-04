package com.epitychia.jobstify.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.epitychia.jobstify.adapter.FavoriteJobAdapter
import com.epitychia.jobstify.databinding.ActivityFavoriteJobBinding
import com.epitychia.jobstify.db.DatabaseContract
import com.epitychia.jobstify.db.MappingHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FavoriteJobActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteJobBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoriteJobBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val jobAdapter = FavoriteJobAdapter()
        binding.rvJobs.layoutManager = LinearLayoutManager(this)
        binding.rvJobs.setHasFixedSize(true)
        binding.rvJobs.adapter = jobAdapter
        loadUsersAsync()
    }

    private fun loadUsersAsync() {
        GlobalScope.launch(Dispatchers.Main){
            val deferredJobs = async(Dispatchers.IO){
                val cursor = contentResolver.query(DatabaseContract.JobColumns.CONTENT_URI, null, null, null, null)
                cursor?.let { MappingHelper.mapCursorToArrayList(it) }
            }

            val jobAdapter = FavoriteJobAdapter()
            val jobs = deferredJobs.await()
            if (jobs != null) {
                if (jobs.size > 0){
                    Log.d("jobs", jobs.toString())
                    jobAdapter.listJob = jobs
                }else{
                    jobAdapter.listJob = ArrayList()
                }
            }
        }
    }
}