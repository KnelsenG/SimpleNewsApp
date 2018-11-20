package ca.sheridancollege.simplenewsapp.util

import android.app.Activity
import android.content.ComponentName
import android.net.Uri
import android.webkit.URLUtil
import androidx.appcompat.content.res.AppCompatResources
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsServiceConnection
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import ca.sheridancollege.simplenewsapp.R
import ca.sheridancollege.simplenewsapp.ext.isPackageAvailable
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

/**
 * Custom Chrome Tabs helper.
 */
class CustomTab @Inject constructor(val activity: Activity) {

    companion object {
        private const val CHROME_STABLE_PACKAGE = "com.android.chrome"
    }

    private var tabClient: CustomTabsClient? = null
    private val tabIntent: CustomTabsIntent by lazy {
        CustomTabsIntent.Builder().apply {

            AppCompatResources.getDrawable(
                activity,
                R.drawable.ic_arrow_back
            )
                ?.toBitmap()
                ?.let { setCloseButtonIcon(it) }

            setToolbarColor(
                ContextCompat.getColor(
                    activity,
                    android.R.color.white
                )
            )

            setStartAnimations(
                activity,
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )

            setExitAnimations(
                activity,
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
        }.build()
    }

    /**
     * Opens the given URL in custom tab.
     *
     * @param url
     */
    fun showTab(url: String) {
        if (URLUtil.isValidUrl(url)) {
            if (activity.isPackageAvailable(CHROME_STABLE_PACKAGE)) {
                bindCustomTabsService(url, CHROME_STABLE_PACKAGE)
            } else {
                val builder = CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(activity, Uri.parse(url))
            }
        } else {
            Snackbar.make(
                activity.window.decorView,
                "Invalid Article",
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    private fun bindCustomTabsService(url: String, chromePackage: String) {
        val connection = object : CustomTabsServiceConnection() {
            override fun onCustomTabsServiceConnected(
                componentName: ComponentName,
                client: CustomTabsClient
            ) {
                tabClient = client
                launchCustomTab(url)
            }

            override fun onServiceDisconnected(name: ComponentName) {
                tabClient = null
            }
        }

        CustomTabsClient.bindCustomTabsService(activity, chromePackage, connection)
    }

    private fun launchCustomTab(url: String) {
        tabClient?.warmup(0L)
        tabIntent.launchUrl(activity, Uri.parse(url))
    }
}