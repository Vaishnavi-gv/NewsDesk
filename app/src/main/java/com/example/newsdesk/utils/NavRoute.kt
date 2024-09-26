package com.example.newsdesk.utils

import com.example.newsdesk.data.model.News
import com.google.gson.Gson
import java.net.URLDecoder
import java.net.URLEncoder
import java.util.Base64

object NavRoute {

    fun createNewsDetailsRoute(news:News,isLocal:Boolean?=false):String{
        val encodedImage= URLEncoder.encode(news.image,"utf-8")
        val encodedUrl= URLEncoder.encode(news.url,"utf-8")
        //val title=Base64.getEncoder().encode(news.title.replace("_","/"))

        val tempNews=news.copy(image=encodedImage,url=encodedUrl)
        val gson= Gson().toJson(tempNews)
        return "/details/news=$gson&isLocal=$isLocal"
    }

    fun getNewsFromRoute(json:String):News{
        val news=Gson().fromJson(json,News::class.java)
        val decodedImage=URLDecoder.decode(news.image,"utf-8")
        val decodeUrl = URLDecoder.decode(news.url, "utf-8")
        return news.copy(image = decodedImage, url = decodeUrl)
    }
}