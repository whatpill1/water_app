package com.example.water_app.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.water_app.databinding.FragmentCategoryBinding
import com.example.water_app.recyclerview.CategoryAdapter
import com.example.water_app.repository.Repository
import com.example.water_app.viewmodel.MainViewModel
import com.example.water_app.viewmodel.MainViewModelFactory

class CategoryFragment : Fragment() {

    // 뷰바인딩
    private lateinit var binding: FragmentCategoryBinding

    //뷰 모델 가져오기
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // 뷰바인딩
        binding = FragmentCategoryBinding.inflate(inflater, container, false)

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
                binding.rvCategory.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.rvCategory.setHasFixedSize(true)
                binding.rvCategory.adapter = CategoryAdapter(requireContext(), donationList)

                // OnClickListener
                val adapter = CategoryAdapter(requireContext(), donationList)

                adapter.setItemClickListener(object : CategoryAdapter.OnItemClickListener {
                    override fun onClick(v: View, position: Int) {
                        activity?.let {
                        }
                    }
                })
                binding.rvCategory.adapter = adapter
            }
            // 통신 실패
            else{
            }
        })
        return binding.root
    }
}