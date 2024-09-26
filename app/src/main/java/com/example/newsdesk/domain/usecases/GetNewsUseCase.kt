package com.example.newsdesk.domain.usecases

import com.example.newsdesk.data.response.NewsResponse
import com.example.newsdesk.domain.repository.NewsRepository
import retrofit2.Response
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(
        language:String,
        text :String?,
        country:String?
    ): NewsResponse {

        val response=newsRepository.getNews(language,text,country)
        if(response.body()==null){
            if(response.code()==404){
                throw Exception("No news found")
            }
            else if(response.code()==500){
                throw Exception("Server Error")
            }
            else if(response.code()==401){
                throw Exception("Unauthorized")
            }
            else if(response.code()==400){
                throw Exception("Bad Request")
            }
            else{
                throw Exception("No news found")
            }
        }
        return newsRepository.getNews(language,text,country).body()!!
    }
}