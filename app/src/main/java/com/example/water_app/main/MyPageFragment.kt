package com.example.water_app.main

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.example.water_app.R
import com.example.water_app.databinding.FragmentMyPageBinding
import com.example.water_app.mypage.HistoryActivity
import com.example.water_app.mypage.MyPageActivity
import com.example.water_app.mypage.NoticeActivity
import com.example.water_app.mypage.PeriodActivity
import com.example.water_app.user.LoginActivity

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

        binding.linNotice.setOnClickListener{
            activity?.let{
                val intent = Intent(context, NoticeActivity::class.java)
                startActivity(intent)
            }
        }

        // Dialog
        binding.linPay.setOnClickListener{
            val mDialogView = LayoutInflater.from(requireContext()).inflate(R.layout.mypage_pay, null)
            val mBuilder = AlertDialog.Builder(requireContext())
                .setView(mDialogView)

            val  mAlertDialog = mBuilder.show()

            val okButton = mDialogView.findViewById<Button>(R.id.btnCall)
            okButton.setOnClickListener {

                Toast.makeText(requireContext(),"서비스 준비중", Toast.LENGTH_LONG).show()
            }

            val noButton = mDialogView.findViewById<Button>(R.id.btnBackCall)
            noButton.setOnClickListener {
                mAlertDialog.dismiss()
            }

            val outButton = mDialogView.findViewById<Button>(R.id.btnOut)
            outButton.setOnClickListener {

                Toast.makeText(requireContext(),"서비스 준비중", Toast.LENGTH_LONG).show()
            }
        }

        binding.linCall.setOnClickListener{
            val mDialogView = LayoutInflater.from(requireContext()).inflate(R.layout.mypage_call, null)
            val mBuilder = AlertDialog.Builder(requireContext())
                .setView(mDialogView)

            val  mAlertDialog = mBuilder.show()

            val okButton = mDialogView.findViewById<Button>(R.id.btnCall)
            okButton.setOnClickListener {

                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("tel:0531111111"))
                startActivity(intent)
            }

            val noButton = mDialogView.findViewById<Button>(R.id.btnBackCall)
            noButton.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }

        binding.linMoney.setOnClickListener{
            val mDialogView = LayoutInflater.from(requireContext()).inflate(R.layout.mypage_money, null)
            val mBuilder = AlertDialog.Builder(requireContext())
                .setView(mDialogView)

            val  mAlertDialog = mBuilder.show()

            val noButton = mDialogView.findViewById<Button>(R.id.btnBackCall)
            noButton.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }

        binding.linlogout.setOnClickListener{
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }
    // 뒤로가기 메인 고정
    private lateinit var callback: OnBackPressedCallback
    lateinit var mainActivity: MainActivity

    // 뒤로가기 고정
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)

        mainActivity = context as MainActivity
    }

    // 뒤로가기 고정
    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }
}