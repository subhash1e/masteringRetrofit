# masteringRetrofit


Firstly add 2 dependency that is required for retrofit and json parsing in gradle file

that is something like this

~~~
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
~~~

__________________________
only one simple xml file which displays get_data from API in textView


~~~
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <TextView
        android:id="@+id/tv1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>


~~~
##### WE NEED ONLY THREE THINGS ###############################################################

~~~
1> DataModel.kt - It is data class which will model data coming from API (id,name,address,etc)
2> ApiInterface.kt -In which we will define a  "getData function" which will fetch data from API
3> MainActivity.kt - To call API and getData function from ApiInterface.kt


------------------
~~~

1>DataModelClass.kt  (DataModel.kt)

~~~
package com.example.masteringretrofit.api

data class DataModelClass(
     var  word:String,
     var definition:String,
    var pronunciation:String
    
/*
    word	"Xylophory"
definition	"Wood-carrying  "
pronunciation	"Slofor"
*/

)
~~~

2>Api.kt (ApiInterface.kt)

~~~
package com.example.masteringretrofit.api

import retrofit2.Call
import retrofit2.http.GET

interface Api {
//   "https://random-words-api.vercel.app/word" 
// you can add variable inside GET("/{var1}") and pass it through
//function getVar1FromApi(var1:String):..
//function getVar2FromApi(var2:String):..
// and so on....

@GET("word")
fun getFromApi(): Call<List<DataModelClass>>
}
~~~

3> MainActivity.kt

~~~
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
         
        myApiCall.getFromApi().enqueue(object : Callback<List<DataModelClass>>{
            override fun onResponse(
                call: Call<List<DataModelClass>>,
                response: Response<List<DataModelClass>>
            ) {
            //good 
                if(response.code()==200){
                   //access the data from api
                   // here I have copied my data to DataModelClass object
                   // so getting JsonObject[0].definition
                   tv1.text = response.body()!![0].definition
                }
            }

            override fun onFailure(call: Call<List<DataModelClass>>, t: Throwable) {
                t.stackTrace
            }
        })



    }
}
~~~

4>> Lastly don't forget to enable Internet uses permission inside manifest file
,

