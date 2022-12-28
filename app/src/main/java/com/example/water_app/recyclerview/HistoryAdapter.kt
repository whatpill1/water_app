package com.example.water_app.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.water_app.databinding.ItemHistoryRecyclerBinding
import com.example.water_app.vo.HistoryData

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    private var historyList = emptyList<HistoryData>()

    class ViewHolder(val binding: ItemHistoryRecyclerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHistoryRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.binding.tvTitle.text = historyList[position].cntr_ttl
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

//    // 데이터 변경시 리스트 다시 할당
//    fun setData(newList: HistoryData){
//        historyList = newList
//        // 새로고침
//        notifyDataSetChanged()
//    }
}