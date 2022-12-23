package com.example.water_app.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.water_app.R
import com.example.water_app.vo.DonationData

class HomeAdapter(private val context: Context) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    var datas = mutableListOf<DonationData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_main_recycler,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val ivImage: ImageView = itemView.findViewById(R.id.ivImage)
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val tvMoney: TextView = itemView.findViewById(R.id.tvMoney)
        private val tvPercent: TextView = itemView.findViewById(R.id.tvPercent)


        fun bind(item: DonationData) {
            Glide.with(itemView).load(item.img).into(ivImage)
            tvTitle.text = item.title
            tvMoney.text = item.money.toString()
            tvPercent.text = item.percent.toString()
        }
    }
}