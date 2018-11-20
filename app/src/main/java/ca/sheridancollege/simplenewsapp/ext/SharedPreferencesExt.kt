package ca.sheridancollege.simplenewsapp.ext

import android.content.SharedPreferences


internal var SharedPreferences.token: String
    set(value) {
        edit().putString("token", value).apply()
    }
    get() {
        return getString("token", "")!!
    }