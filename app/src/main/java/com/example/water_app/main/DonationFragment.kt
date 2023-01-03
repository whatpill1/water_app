package com.example.water_app.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.water_app.communication.CommunicationActivity
import com.example.water_app.databinding.FragmentDonationBinding
import com.example.water_app.recyclerview.MyDonationAdapter
import com.example.water_app.repository.Repository
import com.example.water_app.viewmodel.MainViewModel
import com.example.water_app.viewmodel.MainViewModelFactory


class DonationFragment : Fragment() {

    private lateinit var donationRecyclerView: RecyclerView

    // 뷰바인딩
    private lateinit var binding: FragmentDonationBinding

    //뷰 모델 가져오기
    private lateinit var viewModel : MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // 뷰바인딩
        binding = FragmentDonationBinding.inflate(inflater, container, false)

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
                binding.rvDonation.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.rvDonation.setHasFixedSize(true)
                binding.rvDonation.adapter = MyDonationAdapter(requireContext(), donationList)

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
                binding.rvDonation.adapter = adapter
            }
            // 통신 실패
            else{
            }
        })
        return binding.root
    }
}