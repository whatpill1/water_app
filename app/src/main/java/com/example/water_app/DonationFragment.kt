package com.example.water_app

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView


class DonationFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_donation, null)
        val ivDonation = view.findViewById<ImageView>(R.id.ivDonation)

        ivDonation.setOnClickListener{
            activity?.let{
                val intent = Intent(context, CommunicationActivity::class.java)
                startActivity(intent)
            }
        }

        return view
    }
}