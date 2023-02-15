package com.example.newsbyretrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://newsapi.org/" //common in all http queries
const val API_KEY = "31c5e32d26264d46ade19f3c5317929b"

//this is interface used to tell the retrofit that we only using these queries specified inside the interface
interface NewsInterface {


    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadLines(@Query("country") country : String , @Query("page") page : Int) : Call<News>

    // https://newsapi.org/v2/top-headlines?apiKey=31c5e32d26264d46ade19f3c5317929b&country=in&page=1
}

//singleton object of NewsService
object NewsService{
    val newsInstance : NewsInterface
    init {

        //this is retrofit object
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) //retrofit returns JSON objects we need to convert them in JAVA/KOTLIN objects so we use Gson ConverterFactory
            .build()

        newsInstance= retrofit.create(NewsInterface::class.java) //to tell retrofit object that we are implementing this interface
    }


}