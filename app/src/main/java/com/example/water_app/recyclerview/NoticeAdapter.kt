package com.example.water_app.recyclerview

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.water_app.databinding.ItemNoticeRecyclerBinding
import com.example.water_app.model.NoticeData
import com.example.water_app.mypage.NoticeContentActivity

class NoticeAdapter(private val context: Context, private var noticeList: List<NoticeData>?) : RecyclerView.Adapter<NoticeAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemNoticeRecyclerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeAdapter.ViewHolder {
        val binding = ItemNoticeRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvTitle.text = noticeList?.get(position)?.notice_ttl

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)

            //인텐트 putextra getextra 하는 부분
            val intent = Intent(holder.itemView?.context, NoticeContentActivity::class.java)

            intent.putExtra("notice_ttl",noticeList?.get(position)?.notice_ttl)
            intent.putExtra("notice_cn",noticeList?.get(position)?.notice_cn)

            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
    }

    override fun getItemCount(): Int {
        return noticeList!!.size
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