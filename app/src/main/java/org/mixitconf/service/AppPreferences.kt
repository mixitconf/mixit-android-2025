package org.mixitconf.service

import android.content.SharedPreferences
import androidx.core.content.edit
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


object AppPreferences : KoinComponent {
    private val prefs: SharedPreferences by inject()

    const val KEY_FAVORITE_NOTIFICATION = "talk_notification"
    const val KEY_DATA = "sync_data"
    const val KEY_YEAR = "sync_year"

    var mayNotifyBeforeTalkStart: Boolean
        get() = getBoolean(KEY_FAVORITE_NOTIFICATION, true)
        set(value) = putBoolean(KEY_FAVORITE_NOTIFICATION, value)

    var maySyncDataInBackground: Boolean
        get() = getBoolean(KEY_DATA, true)
        set(value) = putBoolean(KEY_DATA, value)

    var synchronizedYear: String?
        get() = getString(KEY_YEAR, "2024")
        set(value) = putString(KEY_YEAR, value)

    private fun getBoolean(key: String, default: Boolean) =
        prefs.getBoolean(key, default)

    private fun putBoolean(key: String, value: Boolean) =
        prefs.edit { putBoolean(key, value) }

    private fun getString(key: String, default: String) =
        prefs.getString(key, default)

    private fun putString(key: String, value: String?) =
        prefs.edit { putString(key, value) }
}
