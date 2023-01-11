package com.example.water_app.etc

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.water_app.databinding.ActivityCommunicationBinding.inflate
import com.example.water_app.databinding.FragmentToastLoginBinding
import com.example.water_app.user.LoginActivity

class ToastLoginFragment : Fragment() {

    // 뷰바인딩
    private lateinit var binding: FragmentToastLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // 뷰바인딩
        binding = FragmentToastLoginBinding.inflate(inflater, container, false)

        binding.btnLogin.setOnClickListener{
            activity?.let{
                val intent = Intent(context, LoginActivity::class.java)
                startActivity(intent)
            }
        }

        return binding.root
    }
}