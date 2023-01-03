package com.example.water_app.vo

data class JoinData(
    val mbr_id: String,
    val mbr_password: String,
//    val mbr_se : Int,
    val mbr_nm: String,
    val mbr_ncnm: String,
//    val mbr_gen : String,
    val mbr_tel: String,
    val mbr_brthdy: String,
    val mbr_email: String
)

data class JoinResponse(
    val code: Int?,
    val meta: String?,
    val data: JoinData?)
