package com.example.water_app.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.water_app.R
import com.example.water_app.databinding.ActivityMainBinding
import com.example.water_app.home.AppDatabase
import com.example.water_app.home.SubmitActivity
import com.example.water_app.home.ToDoEntity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding : ActivityMainBinding

    private lateinit var db : AppDatabase
//    private lateinit var todoDao
    private lateinit var todoList : ArrayList<ToDoEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        binding.btnAdd.setOnclickListener {
//            val intent = Intent(this, SubmitActivity::class.java)
//            startActivity(intent)
//        }
//
//        db = AppDatabase.getInstance(this)!!
//        todoDao = db.getTodoDao()
//
//        getAllTodoList()
    }

//    private fun getAllTodoList() {
//        Thread{
//            todoList = ArrayList(todoDao.getAll())
//            setRecyclerView()
//        }.start()
//    }
    private fun setRecyclerView(){
        // 리사이클러뷰 설정
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}