package com.example.water_app.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.water_app.R
import com.example.water_app.databinding.FragmentHomeBinding
import com.example.water_app.home.SubmitActivity
import com.example.water_app.recyclerview.HomeAdapter
import com.example.water_app.recyclerview.MyViewPagerAdapter
import com.example.water_app.recyclerview.ViewPagerAdapter
import com.example.water_app.vo.HomeData

class HomeFragment : Fragment() {

    // 뷰바인딩
    private lateinit var binding: FragmentHomeBinding

    // MainActivity 가져오기
    lateinit var mainActivity: MainActivity

    //뷰페이저
    private val sliderImageHandler: Handler = Handler()
    private val sliderImageRunnable = Runnable { binding.ivBanner.currentItem = binding.ivBanner.currentItem + 1 }




    private fun getAespaMembers(): ArrayList<Int> {
        return arrayListOf<Int>(
            R.drawable.banner1,
            R.drawable.store2,
            R.drawable.store3,
            R.drawable.store4)
    }

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
//        binding.ivBanner.adapter = MyViewPagerAdapter(getAespaMembers()) // 어댑터 생성
//        binding.ivBanner.orientation = ViewPager2.ORIENTATION_HORIZONTAL // 방향을 가로로

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

        val homeList = arrayListOf(
            HomeData(R.drawable.my_document, "제목1", "100만원", "10%"),
            HomeData(R.drawable.my_document, "제목2", "200만원", "20%"),
            HomeData(R.drawable.my_document, "제목3", "300만원", "30%"),
            HomeData(R.drawable.my_document, "제목4", "400만원", "40%")
        )


        binding.rvDonation.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvDonation.setHasFixedSize(true)
        binding.rvDonation.adapter = HomeAdapter(requireContext(), homeList)

        // OnClickListener
        val adapter = HomeAdapter(requireContext(), homeList)

        adapter.setItemClickListener(object : HomeAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                activity?.let{
                    val intent = Intent(context, SubmitActivity::class.java)
                    startActivity(intent)
                }
            }
        })

        binding.rvDonation.adapter = adapter

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