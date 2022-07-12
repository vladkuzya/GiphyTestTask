package com.example.giphytesttask.di.modules

import android.content.Context
import com.example.giphytesttask.BuildConfig
import com.example.giphytesttask.data.GiphyApi
import com.example.giphytesttask.data.repository.GiphyRepository
import com.example.giphytesttask.util.Network
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    private val baseUrl = "https://api.giphy.com/v1/gifs/"
    private val apiKeyName = "api_key"
    private val apiKeyValue = "rYCgY8aRZo6BDnbHEtTcQHM7l8PLGzVw"
    private val limitKey = "limit"
    private val limitValue = 25

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(
                LoggingInterceptor.Builder()
                    .setLevel(Level.BODY).build()
            )
        }
        builder.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val original: Request = chain.request()
                val originalHttpUrl: HttpUrl = original.url

                val url: HttpUrl = originalHttpUrl.newBuilder()
                    .addQueryParameter(apiKeyName, apiKeyValue)
                    .addQueryParameter(limitKey, limitValue.toString())
                    .addQueryParameter("rating", "g")
                    .addQueryParameter("offset", "0")
                    .addQueryParameter("lang", "en")
                    .build()

                val requestBuilder: Request.Builder = original.newBuilder()
                    .url(url)

                val request: Request = requestBuilder.build()
                return chain.proceed(request)
            }

        })
        return builder.build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitService(retrofit: Retrofit): GiphyApi {
        return retrofit.create(GiphyApi::class.java)
    }

    @Provides
    fun provideGiphyRepository(giphyApi: GiphyApi, network: Network): GiphyRepository {
        return GiphyRepository(giphyApi, network)
    }

    @Singleton
    @Provides
    fun provideNetwork(context: Context): Network {
        return Network(context)
    }


}