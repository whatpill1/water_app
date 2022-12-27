package com.example.water_app.main

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.example.water_app.databinding.FragmentMapBinding
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView


class MapFragment : Fragment() {

    private lateinit var binding: FragmentMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMapBinding.inflate(inflater, container, false)

        // 맵 띄우기
        val mapView = MapView(requireActivity())
        val mapViewContainer = binding.mapLayout as ViewGroup
        mapViewContainer.addView(mapView)

        // 중심점 변경
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord( 35.893545384707764, 128.61185594900502), true);

        // 줌 레벨 변경
        mapView.setZoomLevel(1, true);

        // 위치 마커로 표시
        val MARKER_POINT = MapPoint.mapPointWithGeoCoord(35.893545384707764, 128.61185594900502)

        // 마커
        val marker = MapPOIItem()
        marker.itemName = "클릭된 장소"
        marker.tag = 0
        marker.mapPoint = MARKER_POINT

        // 기본으로 뜨는 파란색 마커
        marker.markerType = MapPOIItem.MarkerType.BluePin

        // 파란색 마커 클릭했을 때 나타나는 빨간색 마커
        marker.selectedMarkerType =
            MapPOIItem.MarkerType.RedPin

        mapView.addPOIItem(marker)

        return binding.root
    }
}