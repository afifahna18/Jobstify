package com.epitychia.jobstify.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.epitychia.jobstify.data.DataEntity
import com.epitychia.jobstify.databinding.ItemRowJobBinding
import com.epitychia.jobstify.view.DetailJobActivity

class FavoriteJobAdapter : RecyclerView.Adapter<FavoriteJobAdapter.FavoriteJobViewHolder>() {

    private var listJob = mutableListOf<DataEntity>()

    fun setData(data: List<DataEntity>) {
        if (listJob.size > 0) {
            listJob.clear()
        }
        listJob.addAll(data)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteJobViewHolder {
        val itemRowJobBinding =
            ItemRowJobBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteJobViewHolder(itemRowJobBinding)
    }

    override fun onBindViewHolder(holder: FavoriteJobViewHolder, position: Int) {
        val data = listJob[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listJob.size
    }

    inner class FavoriteJobViewHolder(private var binding: ItemRowJobBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: DataEntity) {
            with(binding) {
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