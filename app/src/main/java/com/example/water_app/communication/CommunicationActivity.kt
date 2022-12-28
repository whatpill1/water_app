package com.example.water_app.communication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.water_app.R
import com.example.water_app.databinding.ActivityCommunicationBinding
import com.example.water_app.databinding.FragmentMapBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.flow.combine

class CommunicationActivity : AppCompatActivity() {

    // 뷰바인딩
    private lateinit var binding: ActivityCommunicationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_communication)

        // 뷰바인딩
        binding = ActivityCommunicationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navCom.run { setOnNavigationItemSelectedListener {
            when(it.itemId) {
                // 프래그먼트 이동
                R.id.first -> {
                    val fragment1 = ComIntroduceFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.doContainer, fragment1).commit()
                }
                R.id.second -> {
                    val fragment2 = ComHistoryFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.doContainer, fragment2).commit()
                }
                R.id.third -> {
                    val fragment3 = ComStoryFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.doContainer, fragment3).commit()
                }
            }
            true
        }
            selectedItemId = R.id.first
        }
    }
}