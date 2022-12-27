package com.example.water_app.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.water_app.R

class MyViewPagerAdapter(var aespaMembers: ArrayList<Int>) :
    RecyclerView.Adapter<MyViewPagerAdapter.PagerViewHolder>() {


    inner class PagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.fragment_image_slide, parent, false)) {
        val aespaMember = itemView.findViewById<ImageView>(R.id.img_slide_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PagerViewHolder((parent))

    override fun getItemCount(): Int = aespaMembers.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.aespaMember.setImageResource(aespaMembers[position])
    }
}