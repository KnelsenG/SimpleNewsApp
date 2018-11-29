package ca.sheridancollege.simplenewsapp.di.module.core

import android.content.Context
import android.content.SharedPreferences
import ca.sheridancollege.simplenewsapp.data.remote.HttpAuthInterceptor
import ca.sheridancollege.simplenewsapp.data.remote.NewsRetrofit
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Provides
    @Singleton
    fun provideHipsrvRetrofit(@Named("Auth") okHttpClient: OkHttpClient): NewsRetrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(NewsRetrofit.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        return retrofit.create(NewsRetrofit::class.java)
    }

    @Provides
    @Named("Auth")
    @Singleton
    fun provideAuthHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        httpAuthInterceptor: HttpAuthInterceptor,
        cache: Cache
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(httpAuthInterceptor)
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .cache(cache)
            .build()

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    @Provides
    fun provideHttpAuthInterceptor(): HttpAuthInterceptor {
        return HttpAuthInterceptor()
    }

    @Provides
    fun provideCache(context: Context): Cache = Cache(context.cacheDir, 5 * 1024 * 1024)
}