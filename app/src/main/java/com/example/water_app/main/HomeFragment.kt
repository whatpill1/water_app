package com.example.water_app.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.water_app.R
import com.example.water_app.recyclerview.HomeAdapter
import com.example.water_app.vo.HomeData

class HomeFragment : Fragment() {

    private lateinit var donationRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container,false)

        val homeList = arrayListOf(
            HomeData(R.drawable.my_document, "제목1", "100만원", "10%"),
            HomeData(R.drawable.my_document, "제목2", "200만원", "20%"),
            HomeData(R.drawable.my_document, "제목3", "300만원", "30%"),
            HomeData(R.drawable.my_document, "제목4", "400만원", "40%")
        )

        donationRecyclerView = view.findViewById(R.id.rvDonation)

        donationRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        donationRecyclerView.setHasFixedSize(true)
        donationRecyclerView.adapter = HomeAdapter(requireContext(), homeList)

        return view
    }
}