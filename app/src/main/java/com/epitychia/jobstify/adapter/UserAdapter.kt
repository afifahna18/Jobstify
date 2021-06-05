package com.epitychia.jobstify.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.epitychia.jobstify.databinding.ItemRowUserBinding
import com.epitychia.jobstify.model.User

class UserAdapter(private val listUser : ArrayList<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    fun setUsers(users: List<User>?){
        if(users == null) return
        this.listUser.clear()
        this.listUser.addAll(users)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemRowUserBinding = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(itemRowUserBinding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = listUser[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

   inner class UserViewHolder(private val binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User){
            binding.apply {
                tvName.text = user.name
                tvUsername.text = user.online
                tvCategory.text = user.status
            }
        }
    }
}