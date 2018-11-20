package ca.sheridancollege.simplenewsapp.ext

import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

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

private fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(this, message, duration).show()

fun Context.shortToast(message: String) = toast(message, Toast.LENGTH_SHORT)

fun Context.longToast(message: String) = toast(message, Toast.LENGTH_LONG)

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
