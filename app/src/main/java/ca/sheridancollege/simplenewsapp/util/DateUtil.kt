package ca.sheridancollege.simplenewsapp.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by gerhard on 04/11/17.
 */

class DateUtil {
    companion object {
        @SuppressLint("SimpleDateFormat") val outSdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        @SuppressLint("SimpleDateFormat") val sdf = SimpleDateFormat("yyyy-MM-dd")
        val weekAgo = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, -7) }
        val today= Calendar.getInstance()
    }
}
