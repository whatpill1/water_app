package com.example.water_app.model

data class PostData (
    val cntr_sn : Int?,
    val mbr_sn : String?,
    val cntr_file_id : String?,
    val cntr_ttl : String?,
    val cntr_obctr : String?,
    val cntr_cn : String?,
    val cntr_adres : String?,
    val cntr_loc_lat : String?,
    val cntr_loc_lng : String?,
    val cntr_str_dt : String?,
    val cntr_end_dt : String?,
    val cntr_category : Int?,
    val lock_yn : String?,
    val rgtr_id : Int?,
//    val rgtr_dt : DateTimeException?,
    val mbfr_id : Int?,
//    val mbfr_dt : DateTimeException?
)