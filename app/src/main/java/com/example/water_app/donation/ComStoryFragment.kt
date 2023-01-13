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
import com.example.water_app.databinding.ActivityRecyclerviewBinding
import com.example.water_app.recyclerview.StoryAdapter
import com.example.water_app.repository.Repository
import com.example.water_app.viewmodel.MainViewModel
import com.example.water_app.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_com_history.recyclerView

class ComStoryFragment : Fragment() {

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

        val cntr_sn = requireActivity().intent.extras!!.getInt("cntr_sn")

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getCommunication(cntr_sn)
        viewModel.getCommunicationResponse.observe(viewLifecycleOwner, Observer {
            // 통신 성공
            if(it.isSuccessful){
                val reviewList = it.body()
                Log.d("---------", "$reviewList")

                //리사이클러뷰
                binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                binding.recyclerView.setHasFixedSize(true)
                binding.recyclerView.adapter = StoryAdapter(requireContext(), reviewList)

                // 구분선
                val dividerItemDecoration =
                    DividerItemDecoration(recyclerView.context, LinearLayoutManager(requireContext()).orientation)

                recyclerView.addItemDecoration(dividerItemDecoration)

                // OnClickListener
                val adapter = StoryAdapter(requireContext(), reviewList)

                adapter.setItemClickListener(object : StoryAdapter.OnItemClickListener{
                    override fun onClick(v: View, position: Int) {
                        activity?.let{
                        }
                    }
                })
                binding.recyclerView.adapter = adapter
            }
        })

        return binding.root
    }
}