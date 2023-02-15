package com.example.newsbyretrofit

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class NewsAdapter(val context : Context, val articles : List<Article> ) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_layout,parent,false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = articles[position]
        holder.newsTitle.text  = article.title
        holder.newsDescription.text = article.description
        Glide.with(context).load(article.urlToImage).into(holder.newsImage)

        holder.itemView.setOnClickListener {
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(context, Uri.parse(article.url))
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var newsImage: ImageView = itemView.findViewById<ImageView>(R.id.newsImage)
        var newsDescription: TextView = itemView.findViewById<TextView>(R.id.newsDescription)
        var newsTitle :  TextView = itemView.findViewById<TextView>(R.id.newsTitle)


    }


}