package com.example.water_app.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.water_app.databinding.ItemNoticeRecyclerBinding
import com.example.water_app.vo.NoticeData

class NoticeAdapter(val noticeList: ArrayList<NoticeData>) : RecyclerView.Adapter<NoticeAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemNoticeRecyclerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeAdapter.ViewHolder {
        val binding = ItemNoticeRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvTitle.text = noticeList.get(position).notice_ttl
    }

    override fun getItemCount(): Int {
        return noticeList.size
    }
}