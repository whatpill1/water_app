package com.example.water_app.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.water_app.R
import com.example.water_app.communication.CommunicationActivity
import com.example.water_app.databinding.FragmentDonationBinding
import com.example.water_app.recyclerview.MyDonationAdapter
import com.example.water_app.vo.MyDonationData


class DonationFragment : Fragment() {

    private lateinit var donationRecyclerView: RecyclerView

    // 뷰바인딩
    private lateinit var binding: FragmentDonationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // 뷰바인딩
        binding = FragmentDonationBinding.inflate(inflater, container, false)

        val donationList = arrayListOf(
            MyDonationData(R.drawable.my_document, "제목1"),
            MyDonationData(R.drawable.my_document, "제목2"),
            MyDonationData(R.drawable.my_document, "제목3"),
            MyDonationData(R.drawable.my_document, "제목4")
        )

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

        return binding.root
    }
}