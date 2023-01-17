package com.example.water_app.recyclerview

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.water_app.databinding.ItemNoticeRecyclerBinding
import com.example.water_app.donation.StoryActivity
import com.example.water_app.model.ReviewData

class StoryAdapter(private val context: Context, private var reviewList: List<ReviewData>?) : RecyclerView.Adapter<StoryAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemNoticeRecyclerBinding) : RecyclerView.ViewHolder(binding.root)

    // 아이템 레이아웃 설정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryAdapter.ViewHolder {
        val binding = ItemNoticeRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    // 내용 입력
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mlrd_sn = reviewList?.get(position)?.mlrd_sn
        val mlrd_file_id = reviewList?.get(position)?.mlrd_file_id
        val mlrd_ttl = reviewList?.get(position)?.mlrd_ttl
        val mlrd_cn = reviewList?.get(position)?.mlrd_cn

        holder.binding.tvTitle.text = mlrd_ttl

        // ClickListener
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)

            // 인텐트 putextra
            val intent = Intent(holder.itemView?.context, StoryActivity::class.java)

            intent.putExtra("mlrd_sn", mlrd_sn)
            intent.putExtra("mlrd_file_id", mlrd_file_id)
            intent.putExtra("mlrd_ttl", mlrd_ttl)
            intent.putExtra("mlrd_cn", mlrd_cn)

            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
    }

    // 리스트 내 아이템 개수
    override fun getItemCount(): Int {
        return reviewList!!.size
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