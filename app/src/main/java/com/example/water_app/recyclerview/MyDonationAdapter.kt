package com.example.water_app.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.water_app.R
import com.example.water_app.databinding.ItemDonationRecyclerBinding
import com.example.water_app.databinding.ItemHistoryRecyclerBinding
import com.example.water_app.vo.MyDonationData

class MyDonationAdapter(private val context: Context, private val donationList: ArrayList<MyDonationData>) : RecyclerView.Adapter<MyDonationAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemDonationRecyclerBinding) : RecyclerView.ViewHolder(binding.root)

    // 아이템 레이아웃 설정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyDonationAdapter.ViewHolder {
        val binding = ItemDonationRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    // 내용 입력
    override fun onBindViewHolder(holder: MyDonationAdapter.ViewHolder, position: Int) {
        holder.binding.ivImage.setImageResource(donationList.get(position).img)
        holder.binding.tvTitle.text = donationList.get(position).title

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    // 리스트 내 아이템 개수
    override fun getItemCount(): Int {
        return donationList.size
    }

    // OnClickListener
    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    private lateinit var itemClickListener : OnItemClickListener

    fun setItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}