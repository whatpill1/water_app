package com.example.water_app.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.water_app.Donation.CommunicationActivity
import com.example.water_app.databinding.ActivityRecyclerviewBinding
import com.example.water_app.recyclerview.MyDonationAdapter
import com.example.water_app.repository.Repository
import com.example.water_app.viewmodel.MainViewModel
import com.example.water_app.viewmodel.MainViewModelFactory


class DonationFragment : Fragment() {

    // 뷰바인딩
    private lateinit var binding: ActivityRecyclerviewBinding

    //뷰 모델 가져오기
    private lateinit var viewModel : MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // 뷰바인딩
        binding = ActivityRecyclerviewBinding.inflate(inflater, container, false)

        //php데이터담은
        //리사이클러뷰 표현 아직 사진 퍼센트 없음
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getDonationList()
        viewModel.getDonationListResponse.observe(viewLifecycleOwner, Observer {
            // 통신 성공
            if(it.isSuccessful){
                val donationList = it.body()

                //리사이클러뷰
                binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.recyclerView.setHasFixedSize(true)
                binding.recyclerView.adapter = MyDonationAdapter(requireContext(), donationList)

                // OnClickListener
                val adapter = MyDonationAdapter(requireContext(), donationList)

                adapter.setItemClickListener(object : MyDonationAdapter.OnItemClickListener{
                    override fun onClick(v: View, position: Int) {
                        activity?.let{
                            val intent = Intent(context, CommunicationActivity::class.java)
                            startActivity(intent)
                        }
                    }
                })
                binding.recyclerView.adapter = adapter
            }
            // 통신 실패
            else{
            }
        })
        return binding.root
    }
    //뒤로가기 메인 고정
    private lateinit var callback: OnBackPressedCallback
    lateinit var mainActivity: MainActivity
    //뒤로가기 고정
    override fun onAttach(context: Context) {
        super.onAttach(context)
        //뒤로가기 고정
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)

        mainActivity = context as MainActivity
    }
    //뒤로가기 고정
    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }
}