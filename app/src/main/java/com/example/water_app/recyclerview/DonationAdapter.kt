package com.example.water_app.recyclerview

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.water_app.donation.CommunicationActivity
import com.example.water_app.databinding.ItemMainRecyclerBinding
import com.example.water_app.model.PostData
import java.text.DecimalFormat

class DonationAdapter(private val context: Context, private var donationList: List<PostData>?) : RecyclerView.Adapter<DonationAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemMainRecyclerBinding) : RecyclerView.ViewHolder(binding.root)

    // 아이템 레이아웃 설정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonationAdapter.ViewHolder {
        val binding = ItemMainRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    // 내용 입력
    override fun onBindViewHolder(holder: DonationAdapter.ViewHolder, position: Int) {
        val cntr_sn = donationList?.get(position)?.cntr_sn
        val cntr_ttl = donationList?.get(position)?.cntr_ttl
        val cntr_cn = donationList?.get(position)?.cntr_cn
        val cntr_obctr = donationList?.get(position)?.cntr_obctr
        val cntr_file_id = donationList?.get(position)?.cntr_file_id
        val ctbny_pc = donationList?.get(position)?.ctbny_pc
        val cntr_str_dt = donationList?.get(position)?.cntr_str_dt
        val cntr_end_dt = donationList?.get(position)?.cntr_end_dt

        holder.binding.tvTitle.text = cntr_ttl
        val decimalFormat = DecimalFormat("#,###")
        holder.binding.tvMoney.text = decimalFormat.format(cntr_obctr)+"원"

        // 이미지 url
        var cntrurl : String = cntr_file_id.toString()
        Glide.with(context).load(cntrurl).into(holder.binding.ivImage)
        holder.binding.ivImage.clipToOutline = true

        // 퍼센트
        if (ctbny_pc == null) {
            holder.binding.tvPercent.text = "0%"
            holder.binding.pbPercent.setProgress(0)
        }else{
            val collectPrice:Int? = ctbny_pc
            val totalPrice:Int? = cntr_obctr
            val pricePercent:Double? = collectPrice!!.toDouble() / totalPrice!! * 100

            holder.binding.tvPercent.text = pricePercent?.toInt().toString() + "%"
            holder.binding.pbPercent.setProgress(pricePercent!!.toInt())
        }

        // ClickListener
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)

            // 인텐트 putextra
            val intent = Intent(holder.itemView?.context,CommunicationActivity::class.java)

            intent.putExtra("cntr_sn",cntr_sn)
            intent.putExtra("cntr_ttl",cntr_ttl)
            intent.putExtra("cntr_cn",cntr_cn)
            intent.putExtra("ctbny_pc",ctbny_pc)
            intent.putExtra("cntr_obctr",cntr_obctr)
            intent.putExtra("cntr_file_id",cntr_file_id)
            intent.putExtra("cntr_str_dt", cntr_str_dt)
            intent.putExtra("cntr_end_dt",cntr_end_dt)

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

    // 데이터 변경시 리스트 다시 할당
    fun setData(newList: PostData){
        donationList = listOf(newList)
        // 새로고침
        notifyDataSetChanged()
    }
}