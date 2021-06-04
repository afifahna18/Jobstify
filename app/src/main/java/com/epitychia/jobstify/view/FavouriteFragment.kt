package com.epitychia.jobstify.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.epitychia.jobstify.JobViewModel
import com.epitychia.jobstify.adapter.FavoriteJobAdapter
import com.epitychia.jobstify.adapter.JobAdapter
import com.epitychia.jobstify.databinding.FragmentFavouriteBinding
import com.epitychia.jobstify.db.DatabaseContract.JobColumns.Companion.CONTENT_URI
import com.epitychia.jobstify.db.MappingHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class FavouriteFragment : Fragment() {

    private lateinit var binding: FragmentFavouriteBinding
//    private lateinit var jobAdapter: FavoriteJobAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[JobViewModel::class.java]
//        val listJobs = viewModel.getJobs()
//
//        val jobAdapter = FavoriteJobAdapter()
//        jobAdapter.setData(listJobs)
//        with(binding.rvJobs) {
//            layoutManager = LinearLayoutManager(context)
//            setHasFixedSize(true)
//            adapter = jobAdapter
//        }

        val jobAdapter = FavoriteJobAdapter()
        binding.rvJobs.layoutManager = LinearLayoutManager(context)
        binding.rvJobs.setHasFixedSize(true)
        binding.rvJobs.adapter = jobAdapter
        loadUsersAsync()

    }

    private fun loadUsersAsync() {
        GlobalScope.launch(Dispatchers.Main){
            val deferredJobs = async(Dispatchers.IO){
                val cursor = requireActivity().contentResolver.query(CONTENT_URI, null, null, null, null)
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


    companion object {
        @JvmStatic
        fun newInstance() =
            FavouriteFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}