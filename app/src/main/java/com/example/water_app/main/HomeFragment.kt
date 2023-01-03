package com.example.water_app.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.water_app.R
import com.example.water_app.databinding.FragmentHomeBinding
import com.example.water_app.home.SubmitActivity
import com.example.water_app.recyclerview.HomeAdapter
import com.example.water_app.recyclerview.ViewPagerAdapter
import com.example.water_app.repository.Repository
import com.example.water_app.viewmodel.MainViewModel
import com.example.water_app.viewmodel.MainViewModelFactory

class HomeFragment : Fragment() {

    // 뷰바인딩
    private lateinit var binding: FragmentHomeBinding

    // MainActivity 가져오기
    lateinit var mainActivity: MainActivity

    //뷰 모델 가져오기
    private lateinit var viewModel : MainViewModel

    //뷰페이저
    private val sliderImageHandler: Handler = Handler()
    private val sliderImageRunnable = Runnable { binding.ivBanner.currentItem = binding.ivBanner.currentItem + 1 }

    // context를 mainActivity에 담음
    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // 뷰바인딩
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        //뷰페이저
        val imageList = arrayListOf<Int>().apply {
            for (i in 0..2) {
                add(R.drawable.banner1)
            }
        }

        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.pageWidth)
        val screenWidth = resources.displayMetrics.widthPixels
        val offsetPx = screenWidth - pageMarginPx - pagerWidth

        binding.ivBanner.apply {
            adapter = ViewPagerAdapter(imageList, binding.ivBanner)
            offscreenPageLimit = 1
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    sliderImageHandler.removeCallbacks(sliderImageRunnable)
                    sliderImageHandler.postDelayed(sliderImageRunnable, 3000)
                }
            })
            setPageTransformer { page, position ->
                page.translationX = position * -offsetPx
            }
        }

        // MainActivity 담음
        mainActivity = context as MainActivity

        //php데이터담은
        //리사이클러뷰 표현 아직 사진 퍼센트 없음
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getDonationList()
        viewModel.getDonationListResponse.observe(viewLifecycleOwner, Observer {
            // 통신 성공
            if(it.isSuccessful){
                val homelist = it.body()
                //리사이클러뷰
                binding.rvDonation.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                binding.rvDonation.setHasFixedSize(true)
                binding.rvDonation.adapter = HomeAdapter(requireContext(), homelist)

                // OnClickListener
                val adapter = HomeAdapter(requireContext(), homelist)

                adapter.setItemClickListener(object : HomeAdapter.OnItemClickListener{
                    override fun onClick(v: View, position: Int) {
                        activity?.let{
                            val intent = Intent(context, SubmitActivity::class.java)
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

        // 카테고리
        binding.btnChild.setOnClickListener{
            mainActivity.openFragment()
        }
        binding.btnOld.setOnClickListener{
            mainActivity.openFragment()
        }
        binding.btnDisabled.setOnClickListener{
            mainActivity.openFragment()
        }
        binding.btnAnimal.setOnClickListener{
            mainActivity.openFragment()
        }
        binding.btnEtc.setOnClickListener{
            mainActivity.openFragment()
        }

        return binding.root
    }
    //뷰페이저
    override fun onResume() {
        super.onResume()
        sliderImageHandler.postDelayed(sliderImageRunnable, 1000)
    }

    override fun onPause() {
        super.onPause()
        sliderImageHandler.removeCallbacks(sliderImageRunnable)
    }
}