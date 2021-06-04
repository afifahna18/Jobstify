package com.epitychia.jobstify.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.epitychia.jobstify.JobCategoryViewModel
import com.epitychia.jobstify.JobViewModel
import com.epitychia.jobstify.adapter.JobAdapter
import com.epitychia.jobstify.databinding.ActivityJobCategoriBinding

class JobCategoriActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJobCategoriBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJobCategoriBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extraCategory = intent.getStringExtra("EXTRA_CATEGORY")

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[JobCategoryViewModel::class.java]
//        val listJobs = extraCategory?.let { viewModel.getJobsCategory(it) }
        val listJobs = viewModel.getJobsCategory(extraCategory!!)



        val jobAdapter = JobAdapter()
        jobAdapter.setData(listJobs)
//        listJobs?.let { jobAdapter.setData(it) }
        with(binding.rvJobs) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = jobAdapter
        }
    }
}