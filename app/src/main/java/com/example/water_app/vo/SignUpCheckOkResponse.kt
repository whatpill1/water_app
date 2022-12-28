package com.example.water_app.vo

data class SignUpCheckOkResponse(
    val uid: Int,
    val is_deleted: Int,
    val created_time: String,
    val updated_time: String,
    val signup_type: String,
    val social_id: String,
    val nickname: String,
    val about: String,
    val gender: String,
    val interests: Int,
    val age: Int,
    val address: String,
    val filename: String,
    val access_token: String
): SignUpCheckResponse

// SignUpCheckErrorResponse.kt
data class SignUpCheckErrorResponse(
    val code: Int,
    val message: String,
    val method: String,
    val url: String
) : SignUpCheckResponse

// SignUpCheckResponse.kt
interface SignUpCheckResponse {
}