package com.example.water_app.main

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
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

    @SuppressLint("MissingInflatedId")
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
        binding.linCall.setOnClickListener{
            // Dialog만들기
            val mDialogView = LayoutInflater.from(requireContext()).inflate(R.layout.mypage_call, null)
            val mBuilder = AlertDialog.Builder(requireContext())
                .setView(mDialogView)
                .setTitle("고객 센터")

            val  mAlertDialog = mBuilder.show()

            val okButton = mDialogView.findViewById<Button>(R.id.btnCall)
            okButton.setOnClickListener {

                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("tel:01041785969"))
                startActivity(intent)
            }

            val noButton = mDialogView.findViewById<Button>(R.id.btnBackCall)
            noButton.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }
        binding.linMoney.setOnClickListener{
            // Dialog만들기
            val mDialogView = LayoutInflater.from(requireContext()).inflate(R.layout.mypage_money, null)
            val mBuilder = AlertDialog.Builder(requireContext())
                .setView(mDialogView)
                .setTitle("연말 정산")

            val  mAlertDialog = mBuilder.show()

            val noButton = mDialogView.findViewById<Button>(R.id.btnBackCall)
            noButton.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }

        binding.linPay.setOnClickListener{
            // Dialog만들기
            val mDialogView = LayoutInflater.from(requireContext()).inflate(R.layout.mypage_pay, null)
            val mBuilder = AlertDialog.Builder(requireContext())
                .setView(mDialogView)
                .setTitle("워터 Pay")

            val  mAlertDialog = mBuilder.show()

            val okButton = mDialogView.findViewById<Button>(R.id.btnCall)
            okButton.setOnClickListener {

                Toast.makeText(requireContext(),"추후 추가 예정", Toast.LENGTH_LONG).show()
            }

            val noButton = mDialogView.findViewById<Button>(R.id.btnBackCall)
            noButton.setOnClickListener {
                mAlertDialog.dismiss()
            }

            val outButton = mDialogView.findViewById<Button>(R.id.btnOut)
            outButton.setOnClickListener {

                Toast.makeText(requireContext(),"추후 추가 예정", Toast.LENGTH_LONG).show()
            }
        }


        return binding.root
    }
}