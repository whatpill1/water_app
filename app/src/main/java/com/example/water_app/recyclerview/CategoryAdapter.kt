package com.example.water_app.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.water_app.R
import com.example.water_app.vo.HomeData


class CategoryAdapter(private val context: Context, private val donationList: ArrayList<HomeData>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    // 아이템 레이아웃 설정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category_recycler,parent,false)
        return ViewHolder(view)
    }

    // 내용 입력
    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        holder.ivImage.setImageResource(donationList.get(position).img)
        holder.tvTitle.text = donationList.get(position).title
        holder.tvMoney.text = donationList.get(position).money
        holder.tvPercent.text = donationList.get(position).percent

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    // 리스트 내 아이템 개수
    override fun getItemCount(): Int {
        return donationList.size
    }

    // 레이아웃 View와 연결
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivImage: ImageView = view.findViewById(R.id.ivImage)
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvMoney: TextView = view.findViewById(R.id.tvMoney)
        val tvPercent: TextView = view.findViewById(R.id.tvPercent)
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