package com.example.filmpopuler_taufan.di

import com.example.filmpopuler_taufan.data.remote.api.MoviesService
import com.example.filmpopuler_taufan.util.ApiInterceptor
import com.example.filmpopuler_taufan.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        //OkHttp merupakan libary yang berfungsi untuk
        //melakukan request ke suatu url
        return OkHttpClient.Builder()
            .addInterceptor(interceptor) //untuk mencatat respons
            // untuk memodifikasi permintaan dan menambahkan apikey dengan setiap permintaan
            .addNetworkInterceptor(ApiInterceptor())
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS).build()
    }

    @Singleton
    @Provides
    //berfungsi untuk mengkonversi dari gson string
    //kedalam object atau sebaliknya
    fun provideGsonConverter(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    /*
    bermula me-request ke base url dengan okHttp
    lalu membangun retrofit berfungsi untuk mengubah string gson
    menjadi suatu object atau sebaliknya
     */
    @Singleton
    @Provides
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttp: OkHttpClient
    ): Retrofit {
        //membuat reftrofit
        return Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .baseUrl(BASE_URL)
            .client(okHttp)
            .build()
    }


    @Singleton
    @Provides
    //dengan retrofit dan proses sebelumya membuat sebuah class
    fun provideMovieService(retrofit: Retrofit): MoviesService =
        retrofit.create(MoviesService::class.java)


}