package com.example.water_app.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.water_app.R
import com.example.water_app.databinding.FragmentHomeBinding
import com.example.water_app.databinding.FragmentMyPageBinding
import com.example.water_app.mypage.HistoryActivity
import com.example.water_app.mypage.MyPageActivity
import com.example.water_app.mypage.PeriodActivity

class MyPageFragment : Fragment() {

    // 뷰바인딩
    private lateinit var binding: FragmentMyPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // 뷰바인딩
        binding = FragmentMyPageBinding.inflate(inflater, container, false)

        val view = inflater.inflate(R.layout.fragment_my_page, null)

        binding.linMyPage.setOnClickListener{
            activity?.let{
                val intent = Intent(context, MyPageActivity::class.java)
                startActivity(intent)
            }
        }

        binding.linHistory.setOnClickListener{
            activity?.let{
                val intent = Intent(context, HistoryActivity::class.java)
                startActivity(intent)
            }
        }

        binding.linPeriod.setOnClickListener{
            activity?.let{
                val intent = Intent(context, PeriodActivity::class.java)
                startActivity(intent)
            }
        }

        return binding.root
    }
}