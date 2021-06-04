package com.epitychia.jobstify.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.epitychia.jobstify.JobViewModel
import com.epitychia.jobstify.adapter.JobAdapter
import com.epitychia.jobstify.databinding.FragmentDashboardJobBinding

class DashboardJobFragment : Fragment() {

    private lateinit var binding: FragmentDashboardJobBinding
    private lateinit var adapter: JobAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_dashboard_user, container, false)
        binding = FragmentDashboardJobBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etSearch.setOnClickListener{
            val moveIntent = Intent(context, SearchActivity::class.java)
            context?.startActivity(moveIntent)
        }

        binding.cvArtist.setOnClickListener {
            val moveIntent = Intent(context, JobCategoriActivity::class.java)
            moveIntent.putExtra("EXTRA_CATEGORY", "Artist")
            context?.startActivity(moveIntent)
        }

        binding.cvIt.setOnClickListener {
            val moveIntent = Intent(context, JobCategoriActivity::class.java)
            moveIntent.putExtra("EXTRA_CATEGORY", "IT")
            context?.startActivity(moveIntent)
        }

        binding.cvChef.setOnClickListener {
            val moveIntent = Intent(context, JobCategoriActivity::class.java)
            moveIntent.putExtra("EXTRA_CATEGORY", "Chef")
            context?.startActivity(moveIntent)
        }

        binding.cvNanny.setOnClickListener {
            val moveIntent = Intent(context, JobCategoriActivity::class.java)
            moveIntent.putExtra("EXTRA_CATEGORY", "Nanny")
            context?.startActivity(moveIntent)
        }

        binding.cvMusician.setOnClickListener {
            val moveIntent = Intent(context, JobCategoriActivity::class.java)
            moveIntent.putExtra("EXTRA_CATEGORY", "Musician")
            context?.startActivity(moveIntent)
        }

        binding.cvActress.setOnClickListener {
            val moveIntent = Intent(context, JobCategoriActivity::class.java)
            moveIntent.putExtra("EXTRA_CATEGORY", "Actress")
            context?.startActivity(moveIntent)
        }

        binding.cvInstructor.setOnClickListener {
            val moveIntent = Intent(context, JobCategoriActivity::class.java)
            moveIntent.putExtra("EXTRA_CATEGORY", "Instructor")
            context?.startActivity(moveIntent)
        }

        binding.cvAuthor.setOnClickListener {
            val moveIntent = Intent(context, JobCategoriActivity::class.java)
            moveIntent.putExtra("EXTRA_CATEGORY", "Author")
            context?.startActivity(moveIntent)
        }

        binding.btnFav.setOnClickListener {
            val intent = Intent(context, FavoriteJobActivity::class.java)
            context?.startActivity(intent)
        }


        if (activity != null) {
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[JobViewModel::class.java]
            val listJobs = viewModel.getJobs()

            val jobAdapter = JobAdapter()
            jobAdapter.setData(listJobs)
            with(binding.rvJobs) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = jobAdapter
            }
        }
    }



    companion object {
        @JvmStatic
        fun newInstance() =
            DashboardJobFragment().apply {
                arguments = Bundle().apply {}

            }
    }


}