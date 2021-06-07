package com.epitychia.jobstify.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.epitychia.jobstify.JobCategoryViewModel
import com.epitychia.jobstify.adapter.JobAdapter
import com.epitychia.jobstify.data.DataEntity
import com.epitychia.jobstify.databinding.ActivityJobCategoriBinding

class JobCategoriActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJobCategoriBinding

    companion object {
        const val EXTRA_CATEGORY = "extra_category"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJobCategoriBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extraCategory = intent.getStringExtra(EXTRA_CATEGORY)

        val title = "$extraCategory Category"
        binding.tvTitle.text = title

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[JobCategoryViewModel::class.java]
//        val listJobs = extraCategory?.let { viewModel.getJobsCategory(it) }
        val listJobs = viewModel.getJobsCategory(extraCategory!!)

        val jobAdapter = JobAdapter()
        if(listJobs.size > 0){
            jobAdapter.setData(listJobs)
//        listJobs?.let { jobAdapter.setData(it) }
            with(binding.rvJobs) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)

            }
        }
        binding.rvJobs.adapter = jobAdapter
        setupEmptyView(binding.emptyView, listJobs)


    }

    fun setupEmptyView(view: View, data: List<DataEntity>) {
        if (data.isEmpty()) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }
}