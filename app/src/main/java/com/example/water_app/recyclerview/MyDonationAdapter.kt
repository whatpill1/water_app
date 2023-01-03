package com.example.water_app.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.water_app.databinding.ItemDonationRecyclerBinding
import com.example.water_app.model.PostData

class MyDonationAdapter(private val context: Context, private val donationList: List<PostData>?) : RecyclerView.Adapter<MyDonationAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemDonationRecyclerBinding) : RecyclerView.ViewHolder(binding.root)

    // 아이템 레이아웃 설정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyDonationAdapter.ViewHolder {
        val binding = ItemDonationRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    // 내용 입력
    override fun onBindViewHolder(holder: MyDonationAdapter.ViewHolder, position: Int) {
//        holder.binding.ivImage.setImageResource(donationList.get(position).img)
        holder.binding.tvTitle.text = donationList?.get(position)?.cntr_ttl

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    // 리스트 내 아이템 개수
    override fun getItemCount(): Int {
        return donationList!!.size
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