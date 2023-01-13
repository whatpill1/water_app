package com.example.water_app.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.water_app.databinding.ItemPeriodRecyclerBinding
import com.example.water_app.model.PostData
import java.text.DecimalFormat

class PeriodAdapter(val periodList: List<PostData>?) : RecyclerView.Adapter<PeriodAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemPeriodRecyclerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeriodAdapter.ViewHolder {
        val binding = ItemPeriodRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PeriodAdapter.ViewHolder, position: Int) {
        holder.binding.tvTitle.text = periodList?.get(position)?.cntr_ttl
        val decimalFormat = DecimalFormat("#,###")
        holder.binding.tvMoney.text = decimalFormat.format( periodList?.get(position)?.cntr_obctr)+"원"
        //holder.binding.tvMoney.text = periodList?.get(position)?.cntr_obctr.toString()
        holder.binding.tvStart.text = periodList?.get(position)?.cntr_str_dt
        holder.binding.tvEnd.text = periodList?.get(position)?.cntr_end_dt
    }

    override fun getItemCount(): Int {
        return periodList!!.size
    }
}