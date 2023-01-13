package com.example.water_app.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.water_app.databinding.ItemCommentRecyclerBinding
import com.example.water_app.model.CommentData
import com.example.water_app.model.PostData

class CommentAdapter(private val context: Context, private var commentList: List<CommentData>?): RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemCommentRecyclerBinding) : RecyclerView.ViewHolder(binding.root)

    // 아이템 레이아웃 설정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCommentRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentAdapter.ViewHolder(binding)
    }

    // 내용 입력
    override fun onBindViewHolder(holder: CommentAdapter.ViewHolder, position: Int) {
        val comt_cn = commentList?.get(position)?.comt_cn
        val mbr_ncnm = commentList?.get(position)?.mbr_ncnm
        val rgtr_dt = commentList?.get(position)?.rgtr_dt

        holder.binding.tvComment.text = comt_cn
        holder.binding.tvNick.text = mbr_ncnm
        holder.binding.tvDate.text = rgtr_dt
    }

    // 리스트 내 아이템 개수
    override fun getItemCount(): Int {
        return commentList!!.size
    }

    // 데이터 변경시 리스트 다시 할당
    fun setData(newList: CommentData){
        commentList = listOf(newList)
        // 새로고침
        notifyDataSetChanged()
    }
}