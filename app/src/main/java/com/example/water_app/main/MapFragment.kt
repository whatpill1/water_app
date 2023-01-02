package com.example.water_app.main

import android.Manifest
import android.app.TaskStackBuilder.create
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationRequest
import android.media.audiofx.AcousticEchoCanceler.create
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.water_app.databinding.FragmentMapBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.location.LocationRequest.create
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import com.google.android.gms.location.*


class MapFragment : Fragment() {

    // 뷰바인딩
    private lateinit var binding: FragmentMapBinding

    // 현재 위치
    private val REQUEST_PERMISSION_LOCATION = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 위치 권한 확인
        checkPermissionForLocation(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 뷰바인딩
        binding = FragmentMapBinding.inflate(inflater, container, false)

        // 맵
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

        //현재 위치 불러오기


        return binding.root
    }

    // 위치 권한 확인
    private fun checkPermissionForLocation(context: Context): Boolean {
        // Android 6.0 Marshmallow 이상에서는 위치 권한에 추가 런타임 권한이 필요
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                true
            } else {
                // 권한이 없으므로 권한 요청 알림 보내기
                ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_PERMISSION_LOCATION)
                false
            }
        } else {
            true
        }
    }

    // 권한 요청 후 결과 처리 로직
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //
            } else {
                Log.d("ttt", "onRequestPermissionsResult() _ 권한 허용 거부")
                Toast.makeText(requireContext(), "권한이 없어 해당 기능을 실행할 수 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}