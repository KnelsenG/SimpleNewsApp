package ca.sheridancollege.simplenewsapp.data.remote

import android.content.SharedPreferences
import ca.sheridancollege.simplenewsapp.ext.token
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by knelseng on 16/03/18.
 */

class HttpAuthInterceptor(private val sharedPreferences: SharedPreferences) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val request = original.newBuilder()
                .header("Authorization", "YaCjxipj_pABU8M-4jFh5k5xsyarHe-9ppsjyrzAAjZO2SX3")
                .build()

        return chain.proceed(request)
    }
}
