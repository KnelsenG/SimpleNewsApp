package ca.sheridancollege.simplenewsapp.ext

import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.view.View
import androidx.fragment.app.Fragment
import ca.sheridancollege.simplenewsapp.util.DateUtil
import com.google.android.material.snackbar.Snackbar
import java.util.*

fun Context.isOnline(): Boolean {
    val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnected
}

fun Context.isPackageAvailable(targetPackage: String): Boolean {
    return try {
        packageManager.getPackageInfo(targetPackage, PackageManager.GET_META_DATA)
        true
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }
}

fun Fragment.snack(
        message: String,
        actionMessage: String = "Dismiss",
        action: (View) -> Unit = { },
        duration: Int = Snackbar.LENGTH_LONG
) {
    view?.let {
        Snackbar
                .make(it, message, duration)
                .setAction(actionMessage) { v -> action(v) }
                .show()
    }
}

fun Calendar.toRFC3339(): String {
    return DateUtil.outSdf.format(time)
}