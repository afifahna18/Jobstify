package com.epitychia.jobstify.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.epitychia.jobstify.adapter.FavoriteJobAdapter
import com.epitychia.jobstify.data.DataEntity
import com.epitychia.jobstify.databinding.FragmentFavouriteBinding
import com.epitychia.jobstify.db.DatabaseContract.JobColumns.Companion.CONTENT_URI
import com.epitychia.jobstify.db.MappingHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavouriteBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFavouriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvJobs.layoutManager = LinearLayoutManager(context)
        binding.rvJobs.setHasFixedSize(true)
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
                    jobAdapter.setData(jobs)
                }
            }
            binding.rvJobs.adapter = jobAdapter
//            setupEmptyView(binding.emptyView, jobs)
            jobs?.let { setupEmptyView(binding.emptyView, it) }
        }
    }

    fun setupEmptyView(view: View, data: List<DataEntity>) {
        if (data.isEmpty()) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            FavoriteFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}