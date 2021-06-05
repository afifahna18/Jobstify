package com.epitychia.jobstify.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.epitychia.jobstify.data.DataEntity
import com.epitychia.jobstify.databinding.ItemRowJobBinding
import com.epitychia.jobstify.view.DetailJobActivity

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>(), Filterable {

    private val listJob = mutableListOf<DataEntity>()
    private var listJobFilter = mutableListOf<DataEntity>()

    fun setData(data: List<DataEntity>?) {
        this.listJob.clear()
        if (data != null) {
            this.listJob.addAll(data)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    listJobFilter = listJob
                    Log.d("listJobFilterSize", listJobFilter.size.toString())
                } else {
                    val resultList = mutableListOf<DataEntity>()
                    for (row in listJob) {
                        if (row.title!!.toLowerCase()
                                .contains(constraint.toString().toLowerCase())
                        ) {
                            resultList.add(row)
                        }
                    }
                    Log.d("listJobFilterSize", listJobFilter.size.toString())
                    listJobFilter = resultList
                    Log.d("listJobFilterSize", listJobFilter.size.toString())
                }
                val filterResults = FilterResults()
                filterResults.values = listJobFilter
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (results != null) {
                    listJobFilter = results.values as MutableList<DataEntity>
                }
                Log.d("listJobFilterSize", listJobFilter.size.toString())
                notifyDataSetChanged()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemRowJobBinding =
            ItemRowJobBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(itemRowJobBinding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val user = listJobFilter[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        Log.d("listJobFilterSize", listJobFilter.size.toString())
        return listJobFilter.size
    }

    inner class SearchViewHolder(private val binding: ItemRowJobBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(dataEntity: DataEntity) {
            binding.apply {
                tvJobName.text = dataEntity.title
                tvCompany.text = dataEntity.company

                Glide.with(itemView.context)
                    .load(dataEntity.poster)
                    .into(imgPoster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailJobActivity::class.java)
                    intent.putExtra(DetailJobActivity.EXTRA_DATA_JOB, dataEntity)
                    itemView.context.startActivity(intent)
                }

            }
        }

    }
}