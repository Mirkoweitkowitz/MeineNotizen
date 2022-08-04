package com.example.meinenotizen.api

import com.firebase.ui.auth.data.model.User
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


const val BASE_URL = "https://gorest.co.in/public/v2/"
//"https://graph.microsoft.com/{version}/{location}/onenote/"
//https://graph.microsoft.com/v1.0/me/onenote/notebooks

// TODO: Token holen
const val API_TOKEN = ""

private val client: OkHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
    val newRequest: Request = chain.request().newBuilder()
        .addHeader("Authorization", "Bearer $API_TOKEN")
        .build()
    chain.proceed(newRequest)
}.build()

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .client(client)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>

    @POST("users")
    suspend fun createUser(@Body user: User)
}
object UserApi {
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}
