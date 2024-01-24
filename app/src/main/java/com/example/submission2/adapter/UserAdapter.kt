package com.example.submission2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submission2.databinding.RowItemBinding
import com.example.submission2.model.DataUser

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val listAdapter = ArrayList<DataUser>()
    private var onItemClickCallBack :OnItemClickCallBack?= null


    interface OnItemClickCallBack{
        fun onItemClicked(data: DataUser)
    }
    fun setOnItemClickCallBack (onItemClickCallBack: OnItemClickCallBack){
        this.onItemClickCallBack= onItemClickCallBack
    }

    inner class UserViewHolder(private val binding: RowItemBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(user: DataUser){
                binding?.root.setOnClickListener{
                    onItemClickCallBack?.onItemClicked(user)
                }
                binding?.apply {
                    Glide.with(itemView)
                        .load(user.avatar)
                        .centerCrop()
                        .into(imgItemAvatar)
                    tvItemName?.text=user.username

                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = RowItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserViewHolder((view))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(listAdapter[position])
    }
    fun setListView(users: ArrayList<DataUser>){
        listAdapter.clear()
        notifyDataSetChanged()
        listAdapter.addAll(users)
    }
    override fun getItemCount(): Int= listAdapter.size



}