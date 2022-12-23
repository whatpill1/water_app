package com.example.water_app.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.water_app.R
import com.example.water_app.mypage.MyPageActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MyPageFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_my_page, null)
        val linMyPage = view.findViewById<LinearLayout>(R.id.linMyPage)

        linMyPage.setOnClickListener{
            activity?.let{
                val intent = Intent(context, MyPageActivity::class.java)
                startActivity(intent)
            }
        }

        return view
    }
}