package com.example.water_app.recyclerview

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.water_app.R
import com.example.water_app.vo.DonationData

class HomeAdapter(private val context: Context, private val donationList: ArrayList<DonationData>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main_recycler,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        holder.ivImage.setImageResource(donationList.get(position).img)
        holder.tvTitle.text = donationList.get(position).title
        holder.tvMoney.text = donationList.get(position).money
        holder.tvPercent.text = donationList.get(position).percent
    }

    override fun getItemCount(): Int {
        return donationList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivImage: ImageView = view.findViewById(R.id.ivImage)
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvMoney: TextView = view.findViewById(R.id.tvMoney)
        val tvPercent: TextView = view.findViewById(R.id.tvPercent)

//        fun bind(item: DonationData) {
//            Glide.with(itemView).load(item.img).into(ivImage)
//            tvTitle.text = item.title
//            tvMoney.text = item.money
//            tvPercent.text = item.percent
//        }
    }
}