package com.example.newsbyretrofit

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ybq.android.spinkit.style.Wave
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var adapter: NewsAdapter
    private var articles  = mutableListOf<Article>()
    lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById<ProgressBar>(R.id.spin_kit)
        val wave = Wave()
        progressBar.setIndeterminateDrawable(wave);
        adapter = NewsAdapter(this@MainActivity, articles)
        newsList.adapter = adapter


        //newsList.layoutManager  = manager
        newsList.layoutManager = LinearLayoutManager(this@MainActivity)
        getNews()
    }

    private fun getNews() {
        val news = NewsService.newsInstance.getHeadLines("in", 1)
        news.enqueue(object : Callback<News> {

            override fun onFailure(call: Call<News>, t: Throwable) {
                progressBar.visibility= View.GONE
                Log.d("tag", "Error! ", t)
            }

            override fun onResponse(call: Call<News>, response: Response<News>) {

                val news = response.body()

                if (news != null) {
                    //Log.d("tag", news.toString())
                    articles.addAll(news.articles)
                    adapter.notifyDataSetChanged()
                    progressBar.visibility= View.GONE
                }
            }


        })
    }
}