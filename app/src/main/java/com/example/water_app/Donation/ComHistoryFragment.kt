package com.example.water_app.Donation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.water_app.R
import com.example.water_app.databinding.FragmentComHistoryBinding
import com.example.water_app.databinding.FragmentComIntroduceBinding

class ComHistoryFragment : Fragment() {

    private lateinit var binding: FragmentComHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // 뷰바인딩
        binding = FragmentComHistoryBinding.inflate(inflater, container, false)

        // get
        val cntr_str_dt = requireActivity().intent.extras!!.getString("cntr_str_dt")
        val cntr_end_dt = requireActivity().intent.extras!!.getString("cntr_end_dt")
        val ctbny_pc = requireActivity().intent.extras!!.getInt("ctbny_pc")
        val cntr_obctr = requireActivity().intent.extras!!.getInt("cntr_obctr")
        Log.d("test----------->","$cntr_str_dt,$cntr_end_dt,$ctbny_pc,$cntr_obctr")

        binding.tvStart.text = cntr_str_dt
        binding.tvEnd.text = cntr_end_dt

        // 퍼센트
        if (ctbny_pc == 0) {
            binding.tvPercent.text = "0%"
            binding.progressBar.setProgress(0)
        }else{
            val collectPrice:Int? = ctbny_pc!!.toInt()
            val totalPrice:Int? = cntr_obctr!!.toInt()
            val pricePercent:Double? = collectPrice!!.toDouble() / totalPrice!! * 100

            binding.tvPercent.text = pricePercent?.toInt().toString() + "%"
            binding.progressBar.setProgress(pricePercent!!.toInt())
        }

        binding.tvTotal.text = cntr_obctr!!.toString()

        return binding.root
    }
}