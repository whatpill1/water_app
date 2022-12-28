package com.example.water_app.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.water_app.R
import com.example.water_app.vo.HistoryData

class HistoryAdapter(val historyList: ArrayList<HistoryData>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history_recycler, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryAdapter.ViewHolder, position: Int) {
        holder.image.setImageResource(historyList.get(position).cntr_file_id)
        holder.title.text = historyList.get(position).cntr_ttl
        holder.money.text = historyList.get(position).cntr_obctr
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.ivImage)
        val title = itemView.findViewById<TextView>(R.id.tvTitle)
        val money = itemView.findViewById<TextView>(R.id.tvMoney)
    }
}