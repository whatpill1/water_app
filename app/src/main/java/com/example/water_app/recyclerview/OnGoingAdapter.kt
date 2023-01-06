package com.example.water_app.recyclerview

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.water_app.Donation.CommunicationActivity
import com.example.water_app.databinding.ItemMainRecyclerBinding
import com.example.water_app.model.PostData

class OnGoingAdapter(private val context: Context, private val donationList: List<PostData>?) : RecyclerView.Adapter<OnGoingAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemMainRecyclerBinding) : RecyclerView.ViewHolder(binding.root)

    // 아이템 레이아웃 설정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnGoingAdapter.ViewHolder {
        val binding = ItemMainRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    // 내용 입력
    override fun onBindViewHolder(holder: OnGoingAdapter.ViewHolder, position: Int) {
//        holder.binding.ivImage.setImageResource(donationList!!.get(position).img)
        holder.binding.tvTitle.text = donationList?.get(position)?.cntr_ttl
        holder.binding.tvMoney.text = donationList?.get(position)?.cntr_obctr.toString()
//        val uri : String = donationList?.get(position)?.cntr_file_id.toString()
//        holder.binding.ivImage.setImageBitmap(uri)
        if (donationList?.get(position)?.ctbny_pc == null) {
            holder.binding.tvPercent.text = "0%"
        }else{
            val a:Int? = donationList?.get(position)?.ctbny_pc
            val b:Int? = donationList?.get(position)?.cntr_obctr
            val c:Double? = a!!.toDouble() / b!! * 100
            val d:Int? = c!!.toInt()
            holder.binding.tvPercent.text = d.toString() + "%"
        }
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