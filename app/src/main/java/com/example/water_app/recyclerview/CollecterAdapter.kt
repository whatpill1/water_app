package com.example.water_app.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.water_app.databinding.ItemCollectRecyclerBinding
import com.example.water_app.model.CollectData
import java.text.DecimalFormat

class CollecterAdapter(private val context: Context, private var collectList: List<CollectData>?) : RecyclerView.Adapter<CollecterAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemCollectRecyclerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCollectRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val btn_pc = collectList?.get(position)?.btr_pc
        val rgtr_dt = collectList?.get(position)?.rgtr_dt
        val mbr_nm = collectList?.get(position)?.mbr_ncnm
        holder.binding.tvNick.text = mbr_nm.toString()
        val decimalFormat = DecimalFormat("#,###")
        holder.binding.tvMoney.text = decimalFormat.format(btn_pc)+"원"
        holder.binding.tvDate.text = rgtr_dt.toString()
    }

    override fun getItemCount(): Int {
        return collectList!!.size
    }
}