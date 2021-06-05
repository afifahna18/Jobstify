package com.epitychia.jobstify.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.epitychia.jobstify.data.DataEntity
import com.epitychia.jobstify.databinding.ItemRowJobBinding
import com.epitychia.jobstify.view.DetailJobActivity

class JobAdapter: RecyclerView.Adapter<JobAdapter.JobViewHolder>() {

    var listJob = ArrayList<DataEntity>()

    fun setData(data: List<DataEntity>) {
        if(data == null) return
        this.listJob.clear()
        this.listJob.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val itemRowJobBinding = ItemRowJobBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobViewHolder(itemRowJobBinding)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val data = listJob[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listJob.size
    }

    inner class JobViewHolder(private val binding: ItemRowJobBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: DataEntity){
            with(binding){
                tvJobName.text = data.title
                tvCompany.text = data.company

                Glide.with(itemView.context)
                    .load(data.poster)
                    .into(imgPoster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailJobActivity::class.java)
                    intent.putExtra(DetailJobActivity.EXTRA_DATA_JOB, data)
                    itemView.context.startActivity(intent)
                }

            }
        }
    }
}