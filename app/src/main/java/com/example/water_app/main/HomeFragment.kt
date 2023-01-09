package com.example.water_app.main

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.water_app.Donation.CategoryFragment
import com.example.water_app.R
import com.example.water_app.databinding.FragmentHomeBinding
import com.example.water_app.recyclerview.DonationAdapter
import com.example.water_app.recyclerview.ViewPagerAdapter
import com.example.water_app.repository.Repository
import com.example.water_app.viewmodel.MainViewModel
import com.example.water_app.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_com_history.*
import kotlinx.android.synthetic.main.item_main_recycler.*

class HomeFragment : Fragment() {

    // 뷰바인딩
    private lateinit var binding: FragmentHomeBinding

    // MainActivity 가져오기
    lateinit var mainActivity: MainActivity

    // 뷰페이저
    private val sliderImageHandler: Handler = Handler()
    private val sliderImageRunnable = Runnable { binding.ivBanner.currentItem = binding.ivBanner.currentItem + 1 }

    private val categoryFragment : Fragment = CategoryFragment()

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

        // 뷰페이저
        val imageList = arrayListOf<Int>().apply {
            for (i in 0..2) {
                add(R.drawable.banner1)
                add(R.drawable.banner2)
                add(R.drawable.banner3)
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

        // 리사이클러뷰
        home()
        homeEnd()

        val transaction = fragmentManager?.beginTransaction()

        // 카테고리
        binding.btnChild.setOnClickListener{
            val result = 0
            setFragmentResult("requestKey", bundleOf("bundleKey" to result))
            parentFragmentManager.beginTransaction()
                .replace(R.id.flContainer, CategoryFragment())
                .commit()
        }
        binding.btnOld.setOnClickListener{
            val result = 1
            setFragmentResult("requestKey", bundleOf("bundleKey" to result))
            parentFragmentManager.beginTransaction()
                .replace(R.id.flContainer, CategoryFragment())
                .commit()
        }
        binding.btnDisabled.setOnClickListener{
            val result = 2
            setFragmentResult("requestKey", bundleOf("bundleKey" to result))
            parentFragmentManager.beginTransaction()
                .replace(R.id.flContainer, CategoryFragment())
                .commit()
        }
        binding.btnAnimal.setOnClickListener{
            val result = 3
            setFragmentResult("requestKey", bundleOf("bundleKey" to result))
            parentFragmentManager.beginTransaction()
                .replace(R.id.flContainer, CategoryFragment())
                .commit()
        }
        binding.btnEtc.setOnClickListener{
            val result = 4
            setFragmentResult("requestKey", bundleOf("bundleKey" to result))
            parentFragmentManager.beginTransaction()
                .replace(R.id.flContainer, CategoryFragment())
                .commit()
        }

        return binding.root
    }

    // 리사이클러뷰 진행중인 기부
    fun home(){
        // 뷰 모델 가져오기
        val viewModel : MainViewModel

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getDonationList()
        viewModel.getDonationListResponse.observe(viewLifecycleOwner, Observer {
            // 통신 성공
            if(it.isSuccessful){
                val homelist = it.body()

                // 리사이클러뷰
                binding.rvDonation.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                binding.rvDonation.setHasFixedSize(true)
                binding.rvDonation.adapter = DonationAdapter(requireContext(), homelist)

                // OnClickListener
                val adapter = DonationAdapter(requireContext(), homelist)

                adapter.setItemClickListener(object : DonationAdapter.OnItemClickListener{
                    override fun onClick(v: View, position: Int) {
                        activity?.let{
                        }
                    }
                })
                binding.rvDonation.adapter = adapter
            }
            // 통신 실패
            else{

            }
        })
    }

    // 완료된 기부
    fun homeEnd(){
        val viewModel : MainViewModel
        // php데이터담은
        // 리사이클러뷰 표현 아직 사진 퍼센트 없음
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getHomeEnd()
        viewModel.getHomeEndListResponse.observe(viewLifecycleOwner, Observer {
            // 통신 성공
            if(it.isSuccessful){
                val closelist = it.body()

                // 리사이클러뷰
                binding.rvClose.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                binding.rvClose.setHasFixedSize(true)
                binding.rvClose.adapter = DonationAdapter(requireContext(), closelist)

                // OnClickListener
                val adapter = DonationAdapter(requireContext(), closelist)

                adapter.setItemClickListener(object : DonationAdapter.OnItemClickListener{
                    override fun onClick(v: View, position: Int) {
                        activity?.let{
                        }
                    }
                })
                binding.rvClose.adapter = adapter

            }
            // 통신 실패
            else{

            }
        })
    }

    // 뷰페이저
    override fun onResume() {
        super.onResume()
        sliderImageHandler.postDelayed(sliderImageRunnable, 1000)
    }

    override fun onPause() {
        super.onPause()
        sliderImageHandler.removeCallbacks(sliderImageRunnable)
    }
}