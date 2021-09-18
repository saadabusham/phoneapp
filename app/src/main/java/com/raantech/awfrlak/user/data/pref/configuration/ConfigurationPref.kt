package com.raantech.awfrlak.user.data.pref.configuration

interface ConfigurationPref {

    fun setAppLanguageValue(selectedLanguageValue: String)
    fun getAppLanguageValue():String
}