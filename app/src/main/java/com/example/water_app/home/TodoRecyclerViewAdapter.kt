package com.example.water_app.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

//class TodoRecyclerViewAdapter(private val todoList : ArrayList<ToDoEntity> )
//    : RecyclerView.Adapter<TodoRecyclerViewAdapter.MyViewHolder>(){
//
//        inner class MyViewHolder(binding : ItemTodoBinding) :
//                RecyclerView.ViewHolder(binding.root) {
//                    val tv_importance = binding.tvImportance
//            val tv_title = binding.tvTitle
//
//            val root = binding.root
//                }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val binding: ItemToDoBinding =
//            ItemTodoBinding.inflate(LayoutInflater.from(parent.context),
//            parent, false)
//        return MyViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val todoData = todoList[position]
//        holder.tv_title.text = todoData.title
//    }
//
//    override fun getItemCount(): Int {
//        return todoList.size
//    }
//}