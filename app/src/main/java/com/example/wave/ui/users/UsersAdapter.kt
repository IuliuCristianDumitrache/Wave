package com.example.wave.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wave.databinding.UserListItemBinding
import com.example.wave.extensions.loadWithUri
import com.example.wave.models.Results

class UsersAdapter(private val listener: OnUserItemListener?): PagingDataAdapter<Results, UsersAdapter.UserViewHolder>(DiffUtilCallBack()) {

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return UserViewHolder(binding)
    }

    class UserViewHolder(private val binding: UserListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Results?, listener: OnUserItemListener?) {
            if (item != null ) {
                ViewCompat.setTransitionName(binding.image, "${item.name}${item.id}")
                ViewCompat.setTransitionName(binding.tvName, "${item.location}${item.phone}")
                ViewCompat.setTransitionName(binding.tvLocation, "${item.picture}${item.id}")
                ViewCompat.setTransitionName(binding.root, "${item.picture}${item.location}")
                binding.image.loadWithUri(item.picture?.large)

                val name = item.name?.getFullName()
                binding.tvName.text = name
                val location = item.location?.getFullAddress()
                binding.tvLocation.text = location

                binding.root.setOnClickListener {
                    listener?.onUserItemClicked(binding, item)
                }
            }
        }
    }

    class DiffUtilCallBack: DiffUtil.ItemCallback<Results>() {
        override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem == newItem
        }
    }

    interface OnUserItemListener {
        fun onUserItemClicked(
            binding: UserListItemBinding,
            item: Results
        )
    }
}