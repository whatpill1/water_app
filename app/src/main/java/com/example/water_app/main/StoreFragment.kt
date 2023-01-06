package com.example.water_app.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.example.water_app.R

class StoreFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_store, container, false)
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
}