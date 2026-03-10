package com.n.alian.volleyassignment

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.n.alian.volleyassignment.databinding.UsersItemBinding

@SuppressLint("StaticFieldLeak")
lateinit var binding: UsersItemBinding

class UsersAdapter(val context: Context, val list: ArrayList<Users>) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersAdapter.ViewHolder {
        binding = UsersItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersAdapter.ViewHolder, position: Int) {
        holder.title.text = list[position].title
        holder.isCheck.isEnabled = false
        holder.isCheck.isClickable = false
        holder.isCheck.isChecked = list[position].checkBox


    }

    override fun getItemCount(): Int {
        return list.size
    }


    class ViewHolder(item: UsersItemBinding) : RecyclerView.ViewHolder(item.root) {
        val title = binding.title
        val isCheck = binding.checkBok


    }
}
