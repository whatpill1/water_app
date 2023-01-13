package com.example.water_app.donation

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.water_app.databinding.FragmentComHistoryBinding
import com.example.water_app.recyclerview.CollecterAdapter
import com.example.water_app.repository.Repository
import com.example.water_app.viewmodel.MainViewModel
import com.example.water_app.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_recyclerview.*

class ComHistoryFragment : Fragment() {

    private lateinit var binding: FragmentComHistoryBinding
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // 뷰바인딩
        binding = FragmentComHistoryBinding.inflate(inflater, container, false)

        // get
        val cntr_str_dt = requireActivity().intent.extras!!.getString("cntr_str_dt")
        val cntr_end_dt = requireActivity().intent.extras!!.getString("cntr_end_dt")
        val ctbny_pc = requireActivity().intent.extras!!.getInt("ctbny_pc")
        val cntr_obctr = requireActivity().intent.extras!!.getInt("cntr_obctr")
        val cntr_sn = requireActivity().intent.extras!!.getInt("cntr_sn")

        Log.d("---", "$cntr_sn")

        binding.tvStart.text = cntr_str_dt
        binding.tvEnd.text = cntr_end_dt

        // 퍼센트
        if (ctbny_pc == 0) {
            binding.tvPercent.text = "0%"
            binding.progressBar.setProgress(0)
        }else{
            val collectPrice:Int? = ctbny_pc!!.toInt()
            val totalPrice:Int? = cntr_obctr!!.toInt()
            val pricePercent:Double? = collectPrice!!.toDouble() / totalPrice!! * 100

            binding.tvPercent.text = pricePercent?.toInt().toString() + "%"
            binding.progressBar.setProgress(pricePercent!!.toInt())
        }

        binding.tvTotal.text = cntr_obctr!!.toString()

        // 리사이클러뷰 통신
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getCollecter(cntr_sn)
        viewModel.getCollecterResponse.observe(viewLifecycleOwner, Observer {
            // 통신 성공
            if(it.isSuccessful){
                val collectList = it.body()

                Log.d("---", "$collectList")

                // 리사이클러뷰
                binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                binding.recyclerView.setHasFixedSize(true)  // 성능 개선
                binding.recyclerView.adapter = CollecterAdapter(requireContext(), collectList)
            }
        })

        return binding.root
    }

    // 세로 간격
    class RecyclerViewDecoration(private val divHeight: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.bottom = divHeight
        }
    }
}