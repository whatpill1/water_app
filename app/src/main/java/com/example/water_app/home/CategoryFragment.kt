package com.example.water_app.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.water_app.R
import com.example.water_app.databinding.FragmentCategoryBinding
import com.example.water_app.recyclerview.CategoryAdapter
import com.example.water_app.recyclerview.DonationAdapter
import com.example.water_app.recyclerview.HomeAdapter
import com.example.water_app.vo.HomeData
import com.example.water_app.vo.PostData

class CategoryFragment : Fragment() {

    // 뷰바인딩
    private lateinit var binding: FragmentCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // 뷰바인딩
        binding = FragmentCategoryBinding.inflate(inflater, container, false)

        val donationList = arrayListOf(
            HomeData(R.drawable.my_document, "제목1", "100만원", "10%"),
            HomeData(R.drawable.my_document, "제목2", "200만원", "20%"),
            HomeData(R.drawable.my_document, "제목3", "300만원", "30%"),
            HomeData(R.drawable.my_document, "제목4", "400만원", "40%"),
            HomeData(R.drawable.my_document, "제목5", "100만원", "10%"),
            HomeData(R.drawable.my_document, "제목6", "200만원", "20%"),
            HomeData(R.drawable.my_document, "제목7", "300만원", "30%"),
            HomeData(R.drawable.my_document, "제목8", "400만원", "40%")
        )

        binding.rvCategory.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvCategory.setHasFixedSize(true)
        binding.rvCategory.adapter = CategoryAdapter(requireContext(), donationList)

        // OnClickListener
        val adapter = CategoryAdapter(requireContext(), donationList)

        adapter.setItemClickListener(object : CategoryAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                activity?.let {
                    val intent = Intent(context, SubmitActivity::class.java)
                    startActivity(intent)
                }
            }
        })

        binding.rvCategory.adapter = adapter

        return binding.root
    }
}