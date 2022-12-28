package com.example.water_app.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.water_app.R
import com.example.water_app.vo.HistoryData
import com.example.water_app.vo.PeriodData

class PeriodAdapter(val periodList: ArrayList<PeriodData>) : RecyclerView.Adapter<PeriodAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeriodAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_period_recycler, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PeriodAdapter.ViewHolder, position: Int) {
        holder.image.setImageResource(periodList.get(position).cntr_file_id)
        holder.title.text = periodList.get(position).cntr_ttl
        holder.money.text = periodList.get(position).cntr_obctr
        holder.startDate.text = periodList.get(position).cntr_str_dt
        holder.endDate.text = periodList.get(position).cntr_end_dt
    }

    override fun getItemCount(): Int {
        return periodList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.ivImage)
        val title = itemView.findViewById<TextView>(R.id.tvTitle)
        val money = itemView.findViewById<TextView>(R.id.tvMoney)
        val startDate = itemView.findViewById<TextView>(R.id.tvStart)
        val endDate = itemView.findViewById<TextView>(R.id.tvEnd)
    }
}