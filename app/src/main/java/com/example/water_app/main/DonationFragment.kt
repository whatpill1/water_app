package com.example.water_app.main

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.water_app.Donation.CommunicationActivity
import com.example.water_app.databinding.ActivityRecyclerviewBinding
import com.example.water_app.recyclerview.DonationAdapter
import com.example.water_app.repository.Repository
import com.example.water_app.viewmodel.MainViewModel
import com.example.water_app.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_recyclerview.*


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
                binding.recyclerView.adapter = DonationAdapter(requireContext(), donationList)

                // OnClickListener
                val adapter = DonationAdapter(requireContext(), donationList)

                adapter.setItemClickListener(object : DonationAdapter.OnItemClickListener{
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

    class GridSpaceItemDecoration(private val spanCount: Int, private val space: Int): RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val position = parent.getChildAdapterPosition(view)
            val column = position % spanCount + 1      // 1부터 시작

            /** 첫번째 행(row-1)에 있는 아이템인 경우 상단에 [space] 만큼의 여백을 추가한다 */
            if (position < spanCount){
                outRect.top = space
            }
            /** 마지막 열(column-N)에 있는 아이템인 경우 우측에 [space] 만큼의 여백을 추가한다 */
            if (column == spanCount){
                outRect.right = space
            }
            /** 모든 아이템의 좌측과 하단에 [space] 만큼의 여백을 추가한다. */
            outRect.left = space
            outRect.bottom = space

        }

    }
}