package com.example.water_app.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.water_app.databinding.ItemHistoryRecyclerBinding
import com.example.water_app.model.PostData
import java.text.DecimalFormat


class HistoryAdapter(private val context: Context, private var historyList: List<PostData>?) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemHistoryRecyclerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHistoryRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvTitle.text = historyList?.get(position)?.cntr_ttl
        val decimalFormat = DecimalFormat("#,###")
        holder.binding.tvMoney.text = decimalFormat.format( historyList?.get(position)?.cntr_obctr)+"원"
        //holder.binding.tvMoney.text = historyList?.get(position)?.cntr_obctr.toString()
    }

    override fun getItemCount(): Int {
        return historyList!!.size
    }

    // 데이터 변경시 리스트 다시 할당
    fun setData(newList: PostData){
        historyList = listOf(newList)
        // 새로고침
        notifyDataSetChanged()
    }
}