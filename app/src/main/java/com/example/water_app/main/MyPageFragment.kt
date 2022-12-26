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