package com.example.water_app.recyclerview

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.water_app.communication.CommunicationActivity
import com.example.water_app.databinding.ItemMainRecyclerBinding
import com.example.water_app.home.SubmitActivity
import com.example.water_app.model.PostData

class HomeAdapter(private val context: Context, private val donationList: List<PostData>?) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemMainRecyclerBinding) : RecyclerView.ViewHolder(binding.root)

    // 아이템 레이아웃 설정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val binding = ItemMainRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    // 내용 입력
    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
//        holder.binding.ivImage.setImageResource(donationList!!.get(position).img)
        holder.binding.tvTitle.text = donationList?.get(position)?.cntr_ttl
        holder.binding.tvMoney.text = donationList?.get(position)?.cntr_obctr.toString()
//        holder.binding.tvPercent.text = donationList?.get(position)?.percent

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)

            //인텐트 putextra getextra 하는 부분
            val intent = Intent(holder.itemView?.context,CommunicationActivity::class.java)

            intent.putExtra("cntr_sn",donationList?.get(position)?.cntr_sn)
            intent.putExtra("cntr_ttl",donationList?.get(position)?.cntr_ttl)
            intent.putExtra("cntr_cn",donationList?.get(position)?.cntr_cn)

            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
    }

    // 리스트 내 아이템 개수
    override fun getItemCount(): Int {
        return donationList!!.size
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