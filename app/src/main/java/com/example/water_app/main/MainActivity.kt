package com.example.water_app.main

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.water_app.R
import com.example.water_app.databinding.ActivityMainBinding
import com.example.water_app.etc.ToastLoginFragment
import com.example.water_app.user.MySharedPreferences
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MainActivity : AppCompatActivity() {

    // 뷰바인딩
    private lateinit var binding: ActivityMainBinding

    // 위치 권한
    private val REQUEST_PERMISSION_LOCATION = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 위치 권한 확인
        checkPermissionForLocation(this)

        // 뷰바인딩
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mbr_sn = MySharedPreferences.getUserSn(this).toInt()
        Log.d("----메인 엑티비티------->","$mbr_sn")

        binding.navMain.run { setOnNavigationItemSelectedListener {
            when(it.itemId) {
                // 프래그먼트 이동
                R.id.first -> {
                    val fragment = HomeFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit()
                }
                R.id.second -> {
                    val fragment1 = DonationFragment()
                    val fragment2 = ToastLoginFragment()

                    if (mbr_sn == -1){
                        supportFragmentManager.beginTransaction().replace(R.id.flContainer, fragment2).commit()
                    }else{
                        supportFragmentManager.beginTransaction().replace(R.id.flContainer, fragment1).commit()
                    }
                }
                R.id.third -> {
                    val fragment = MapFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit()
                }
                R.id.fourth -> {
                    val fragment = StoreFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit()
                }
                R.id.fifth -> {
                    val fragment = MyPageFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit()
                }
            }
            true
        }
            selectedItemId = R.id.first
        }

        // HashKey
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val packageInfo =
                packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNING_CERTIFICATES)
            for (signature in packageInfo.signingInfo.apkContentsSigners) {
                try {
                    val md = MessageDigest.getInstance("SHA")
                    md.update(signature.toByteArray())
                    Log.d("getKeyHash", "key hash: ${Base64.encodeToString(md.digest(), Base64.NO_WRAP)}")
                } catch (e: NoSuchAlgorithmException) {
                    Log.w("getKeyHash", "Unable to get MessageDigest. signature=$signature", e)
                }
            }
        }
    }

    // 위치 권한 확인
    private fun checkPermissionForLocation(context: Context): Boolean {
        // Android 6.0 Marshmallow 이상에서는 위치 권한에 추가 런타임 권한이 필요
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                true
            } else {
                // 권한이 없으므로 권한 요청 알림 보내기
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    REQUEST_PERMISSION_LOCATION
                )
                false
            }
        } else {
            true
        }
    }

    // 위치 권한 요청 후 결과 처리
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //
            } else {
                Log.d("ttt", "onRequestPermissionsResult() _ 권한 허용 거부")
                Toast.makeText(this, "권한이 없어 해당 기능을 실행할 수 없습니다.", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}