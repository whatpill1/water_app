package com.example.water_app.vo

import com.google.gson.annotations.SerializedName

data class HistoryData (
    val cntr_sn : Int,
    val cntr_file_id : Int,
    val cntr_ttl : String,
    val cntr_obctr : String,
    @SerializedName("use_yn")
    val use_yn : Char
)