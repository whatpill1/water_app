package com.example.water_app.donation

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.water_app.R
import com.example.water_app.databinding.FragmentComIntroduceBinding
import com.example.water_app.user.LoginActivity
import com.example.water_app.user.MySharedPreferences
import java.text.DecimalFormat


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

        // get
        val mbr_sn = MySharedPreferences.getUserSn(requireContext()).toInt()
        val cntr_sn = requireActivity().intent.extras!!.getInt("cntr_sn")
        val cntr_ttl = requireActivity().intent.extras!!.getString("cntr_ttl")
        val cntr_cn = requireActivity().intent.extras!!.getString("cntr_cn")
        val ctbny_pc = requireActivity().intent.extras!!.getInt("ctbny_pc")
        val cntr_obctr = requireActivity().intent.extras!!.getInt("cntr_obctr")
        val cntr_file_id = requireActivity().intent.extras!!.getString("cntr_file_id")

        val cntr_cn_replace = cntr_cn?.replace("<h2 style=\"font-style:italic\">","")

        // 인텐트 putextra getextra 하는 부분
        binding.donationTtl.text = cntr_ttl
        binding.donationCn.text = cntr_cn_replace
        val decimalFormat = DecimalFormat("#,###")
        binding.donationAmount.text = decimalFormat.format(cntr_obctr)+"원"

        // 이미지 url
        var cntrurl : String = cntr_file_id.toString()
        Glide.with(this).load(cntrurl).into(binding.donationImage)

        // 퍼센트
        if (ctbny_pc == 0) {
            binding.donationPercent.text = "0%"
            binding.progressBar.setProgress(0)
        }else{
            val collectPrice:Int? = ctbny_pc!!.toInt()
            val totalPrice:Int? = cntr_obctr!!.toInt()
            val pricePercent:Double? = collectPrice!!.toDouble() / totalPrice!! * 100

            binding.donationPercent.text = pricePercent?.toInt().toString() + "%"
            binding.progressBar.setProgress(pricePercent!!.toInt())
        }

        // 인텐트 putextra getextra 하는 부분
        binding.btnDonation.setOnClickListener{
            activity?.let{
                if(mbr_sn == -1){
                    val mDialogView = LayoutInflater.from(requireContext()).inflate(R.layout.toast_login, null)
                    val mBuilder = AlertDialog.Builder(requireContext())
                        .setView(mDialogView)
                    val  mAlertDialog = mBuilder.show()
                    val noButton = mDialogView.findViewById<Button>(R.id.btnBackCall)
                    noButton.setOnClickListener {
                        mAlertDialog.dismiss()
                    }
                    val btnLogin = mDialogView.findViewById<Button>(R.id.btnLogin)
                    btnLogin.setOnClickListener{
                        val intent = Intent(context, LoginActivity::class.java)
                        startActivity(intent)
                    }
                }else{
                    val intent = Intent(context, DonationActivity::class.java)
                    intent.putExtra("cntr_sn",cntr_sn)
                    startActivity(intent)
                }
            }
        }
        return binding.root
    }
}