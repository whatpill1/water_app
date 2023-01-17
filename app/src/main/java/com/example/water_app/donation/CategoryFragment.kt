package com.example.water_app.donation

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.water_app.R
import com.example.water_app.databinding.ActivityRecyclerviewBinding
import com.example.water_app.main.HomeFragment
import com.example.water_app.main.MainActivity
import com.example.water_app.recyclerview.DonationAdapter
import com.example.water_app.repository.Repository
import com.example.water_app.viewmodel.MainViewModel
import com.example.water_app.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_com_history.*

class CategoryFragment : Fragment() {

    // 뷰바인딩
    private lateinit var binding: ActivityRecyclerviewBinding

    // 뷰 모델 가져오기
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // 뷰바인딩
        binding = ActivityRecyclerviewBinding.inflate(inflater, container, false)

        // 카테고리별 결과물
        setFragmentResultListener("requestKey") { requestKey, bundle ->
            val categoryNum = bundle.getInt("bundleKey")
            val repository = Repository()
            val viewModelFactory = MainViewModelFactory(repository)
            viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
            viewModel.getCategory(categoryNum)
            viewModel.getDonationListResponse.observe(viewLifecycleOwner, Observer {
                // 통신 성공
                if (it.isSuccessful) {
                    val donationList = it.body()

                    binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
                    binding.recyclerView.setHasFixedSize(true)
                    binding.recyclerView.adapter = DonationAdapter(requireContext(), donationList)

                    // 아이템 간 간격
                    recyclerView.addItemDecoration(RecyclerViewDecoration(20,20,0,40))

                    // OnClickListener
                    val adapter = DonationAdapter(requireContext(), donationList)

                    adapter.setItemClickListener(object : DonationAdapter.OnItemClickListener {
                        override fun onClick(v: View, position: Int) {
                            activity?.let {
                            }
                        }
                    })
                    binding.recyclerView.adapter = adapter
                }
            })
        }
        return binding.root
    }

    // 뒤로가기 메인 고정
    private lateinit var callback: OnBackPressedCallback
    lateinit var mainActivity: MainActivity

    // 뒤로가기 고정
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.flContainer, HomeFragment())
                    .commit()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)

        mainActivity = context as MainActivity
    }

    // 뒤로가기 고정
    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }

    // 리싸이클러뷰 간격
    class RecyclerViewDecoration(private val divLeft: Int, private val divRight: Int, private val divHeight: Int, private val divTop: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.left = divLeft
            outRect.right = divRight
            outRect.bottom = divHeight
            outRect.top = divTop
        }
    }
}