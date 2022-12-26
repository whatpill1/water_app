package com.example.water_app.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.water_app.R
import com.example.water_app.vo.DonationData

class DonationAdapter(private val context: Context, private val donationList: ArrayList<DonationData>) : RecyclerView.Adapter<DonationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonationAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_donation_recycler,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: DonationAdapter.ViewHolder, position: Int) {
        holder.ivImage.setImageResource(donationList.get(position).img)
        holder.tvTitle.text = donationList.get(position).title
    }

    override fun getItemCount(): Int {
        return donationList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivImage: ImageView = view.findViewById(R.id.ivImage)
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
    }
}