package com.example.water_app.Donation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.water_app.databinding.FragmentComIntroduceBinding
import com.example.water_app.mypage.MyPageActivity


class ComIntroduceFragment : Fragment() {

    // 뷰바인딩
    private lateinit var binding: FragmentComIntroduceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // 뷰바인딩
        binding = FragmentComIntroduceBinding.inflate(inflater, container, false)

        val cntr_sn = requireActivity().intent.extras!!.getString("cntr_sn")
        val cntr_ttl = requireActivity().intent.extras!!.getString("cntr_ttl")
        val cntr_cn = requireActivity().intent.extras!!.getString("cntr_cn")
        //인텐트 putextra getextra 하는 부분

        binding.donationTtl.text = cntr_ttl
        binding.donationCn.text = cntr_cn

        binding.btnDonation.setOnClickListener{
            activity?.let{
                val intent = Intent(context, DonationActivity::class.java)
                startActivity(intent)
            }
        }

        return binding.root
    }
}