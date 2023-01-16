package com.example.water_app.main

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.water_app.R
import com.example.water_app.databinding.FragmentMyPageBinding
import com.example.water_app.mypage.HistoryActivity
import com.example.water_app.mypage.MyPageActivity
import com.example.water_app.mypage.NoticeActivity
import com.example.water_app.mypage.PeriodActivity
import com.example.water_app.user.LoginActivity
import com.example.water_app.user.MySharedPreferences

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

        val mbr_sn = MySharedPreferences.getUserSn(requireContext()).toInt()

        binding.linMyPage.setOnClickListener{
            activity?.let{
                if(mbr_sn == -1) {
                    val mDialogView =
                        LayoutInflater.from(requireContext()).inflate(R.layout.toast_login, null)
                    val mBuilder = AlertDialog.Builder(requireContext())
                        .setView(mDialogView)
                    val mAlertDialog = mBuilder.show()
                    val noButton = mDialogView.findViewById<Button>(R.id.btnBackCall)
                    noButton.setOnClickListener {
                        mAlertDialog.dismiss()
                    }
                    val btnLogin = mDialogView.findViewById<Button>(R.id.btnLogin)
                    btnLogin.setOnClickListener{
                        val intent = Intent(context, LoginActivity::class.java)
                        startActivity(intent)
                    }
                }else {
                    val intent = Intent(context, MyPageActivity::class.java)
                    startActivity(intent)
                }
            }
        }

        binding.linPeriod.setOnClickListener{
            toastService(mbr_sn)
        }

        binding.linNotice.setOnClickListener{
            activity?.let{
                val intent = Intent(context, NoticeActivity::class.java)
                startActivity(intent)
            }
        }

        // Dialog
        binding.linPay.setOnClickListener{
            activity?.let{
                if(mbr_sn == -1) {
                    val mDialogView =
                        LayoutInflater.from(requireContext()).inflate(R.layout.toast_login, null)
                    val mBuilder = AlertDialog.Builder(requireContext())
                        .setView(mDialogView)
                    val mAlertDialog = mBuilder.show()
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
            toastService(mbr_sn)
        }

        if (mbr_sn == -1){
            binding.ivChange.setImageResource(R.drawable.my_login)
            binding.tvChange.setText("로그인")
            binding.linlogout.setOnClickListener{
                val intent = Intent(context, LoginActivity::class.java)
                startActivity(intent)
            }
        }else{
            binding.linlogout.setOnClickListener{
                MySharedPreferences.setUserSn(requireContext(),"-1")
                val intent = Intent(context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }

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

    // 서비스 준비중
    fun toastService(mbr_sn: Int) {
        if (mbr_sn == -1) {
            val mDialogView =
                LayoutInflater.from(requireContext()).inflate(R.layout.toast_login, null)
            val mBuilder = AlertDialog.Builder(requireContext())
                .setView(mDialogView)
            val mAlertDialog = mBuilder.show()
            val noButton = mDialogView.findViewById<Button>(R.id.btnBackCall)
            noButton.setOnClickListener {
                mAlertDialog.dismiss()
            }
        } else {
            val mDialogView =
                LayoutInflater.from(requireContext()).inflate(R.layout.mypage_money, null)
            val mBuilder = AlertDialog.Builder(requireContext())
                .setView(mDialogView)

            val mAlertDialog = mBuilder.show()

            val noButton = mDialogView.findViewById<Button>(R.id.btnBackCall)
            noButton.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }
    }
}