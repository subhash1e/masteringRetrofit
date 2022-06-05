package com.example.masteringretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.masteringretrofit.api.Api
import com.example.masteringretrofit.api.DataModelClass
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tv1:TextView = findViewById(R.id.tv1)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://random-words-api.vercel.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val myApiCall = retrofit.create(Api::class.java)
         val call = myApiCall.getFromApi()
        call.enqueue(object : Callback<List<DataModelClass>>{
            override fun onResponse(
                call: Call<List<DataModelClass>>,
                response: Response<List<DataModelClass>>
            ) {
                if(response.code()==200){
                    tv1.text = response.body()!![0].definition
                }
            }

            override fun onFailure(call: Call<List<DataModelClass>>, t: Throwable) {
                t.stackTrace
            }
        })



    }
}