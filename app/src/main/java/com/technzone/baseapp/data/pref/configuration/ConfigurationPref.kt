package com.technzone.baseapp.data.pref.configuration

interface ConfigurationPref {

    fun setAppLanguageValue(selectedLanguageValue: String)
    fun getAppLanguageValue():String
}