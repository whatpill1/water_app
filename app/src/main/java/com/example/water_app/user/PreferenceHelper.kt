package com.example.water_app.user

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper(context: Context) {
    private val INTRO = "intro"
    private val ID = "mbr_id"
    private val PASS = "mbr_password"
    private val NAME = "mbr_nm"
    private val NICK = "mbr_ncnm"
    private val GEN = "mbr_gen"
    private val TEL = "mbr_tel"
    private val BIRTH = "mbr_brthdy"
    private val EMAIL = "mbr_email"

    private val app_prefs: SharedPreferences
    private val context: Context

    init {
        app_prefs = context.getSharedPreferences("shared", 0)
        this.context = context
    }

    fun putIsLogin(loginOrOut: Boolean) {
        val edit = app_prefs.edit()
        edit.putBoolean(INTRO, loginOrOut)
        edit.apply()
    }

    fun putId(loginOrOut: String?) {
        val edit = app_prefs.edit()
        edit.putString(ID, loginOrOut)
        edit.apply()
    }

    val mbr_id: String?
        get() = app_prefs.getString(ID, "")

    fun putPass(loginOrOut: String?) {
        val edit = app_prefs.edit()
        edit.putString(PASS, loginOrOut)
        edit.apply()
    }

    val mbr_password: String?
        get() = app_prefs.getString(PASS, "")

    fun putName(loginOrOut: String?) {
        val edit = app_prefs.edit()
        edit.putString(NAME, loginOrOut)
        edit.apply()
    }

    val mbr_nm: String?
        get() = app_prefs.getString(NAME, "")

    fun putNick(loginOrOut: String?) {
        val edit = app_prefs.edit()
        edit.putString(NICK, loginOrOut)
        edit.apply()
    }

    val mbr_ncnm: String?
        get() = app_prefs.getString(NICK, "")

    fun putGen(loginOrOut: String?) {
        val edit = app_prefs.edit()
        edit.putString(GEN, loginOrOut)
        edit.apply()
    }

    val mbr_gen: String?
        get() = app_prefs.getString(GEN, "")

    fun putTel(loginOrOut: String?) {
        val edit = app_prefs.edit()
        edit.putString(TEL, loginOrOut)
        edit.apply()
    }

    val mbr_tel: String?
        get() = app_prefs.getString(TEL, "")

    fun putBirth(loginOrOut: String?) {
        val edit = app_prefs.edit()
        edit.putString(BIRTH, loginOrOut)
        edit.apply()
    }

    val mbr_brthdy: String?
        get() = app_prefs.getString(BIRTH, "")

    fun putEmail(loginOrOut: String?) {
        val edit = app_prefs.edit()
        edit.putString(EMAIL, loginOrOut)
        edit.apply()
    }

    val mbr_email: String?
        get() = app_prefs.getString(EMAIL, "")



}