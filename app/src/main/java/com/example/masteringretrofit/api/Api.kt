package com.example.masteringretrofit.api

import retrofit2.Call
import retrofit2.http.GET

interface Api {
//    https://run.mocky.io/v3/   517cc163-2a7f-4d02-9192-1814f8ed807d
//    https://run.mocky.io/v3/90ffd397-b2c6-4949-9742-4e89d9459e32
//    https://random-words-api.vercel.app/word
@GET("word")
fun getFromApi(): Call<List<DataModelClass>>
}