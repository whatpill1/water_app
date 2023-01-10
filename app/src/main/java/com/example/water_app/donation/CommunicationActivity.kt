package com.example.water_app.donation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.water_app.R
import com.example.water_app.databinding.ActivityCommunicationBinding

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
                R.id.sixth -> {
                    val fragment6 = ComIntroduceFragment()

                    supportFragmentManager.beginTransaction().replace(R.id.doContainer, fragment6).commit()
                }
                R.id.seventh -> {
                    val fragment7 = ComHistoryFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.doContainer, fragment7).commit()
                }
                R.id.eighth -> {
                    val fragment8 = ComStoryFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.doContainer, fragment8).commit()
                }
            }
            true
        }
            selectedItemId = R.id.sixth
        }
    }
}