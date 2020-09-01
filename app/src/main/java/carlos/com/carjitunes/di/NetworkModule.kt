package carlos.com.carjitunes.di

import carlos.com.carjitunes.BuildConfig
import carlos.com.carjitunes.data.network.Api
import carlos.com.carjitunes.data.network.ITunesSongsAppService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { okhttpClient() }
    single { retrofit(get()) }
    single { apiService(get()) }
    single { createMovieAppService(get()) }
}

fun createMovieAppService(
    api: Api
): ITunesSongsAppService = ITunesSongsAppService(api)

fun apiService(
    retrofit: Retrofit
): Api =
    retrofit.create(Api::class.java)

fun retrofit(
    okHttpClient: OkHttpClient
): Retrofit =
    Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

fun okhttpClient(): OkHttpClient =
    OkHttpClient.Builder()
        .build()


