package com.example.water_app.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.water_app.R
import com.example.water_app.recyclerview.DonationAdapter
import com.example.water_app.vo.DonationData


class DonationFragment : Fragment() {

    private lateinit var donationRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_donation, container, false)

        val donationList = arrayListOf(
            DonationData(R.drawable.my_document, "제목1"),
            DonationData(R.drawable.my_document, "제목2"),
            DonationData(R.drawable.my_document, "제목3"),
            DonationData(R.drawable.my_document, "제목4")
        )

        donationRecyclerView = view.findViewById(R.id.rvDonation)

        donationRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        donationRecyclerView.setHasFixedSize(true)
        donationRecyclerView.adapter = DonationAdapter(requireContext(), donationList)

        return view
    }
}