package com.example.newsdesk.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsdesk.data.model.News
import com.example.newsdesk.data.response.NewsResponse
import com.example.newsdesk.domain.usecases.GetNewsUseCase
import com.example.newsdesk.presentation.State
import com.example.newsdesk.presentation.State.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getNewsUseCase: GetNewsUseCase): ViewModel() {

    private val _state = MutableStateFlow<State<NewsResponse>>(State.Loading)
    val state = _state as StateFlow<State<NewsResponse>>

    private var job: Job? = null

    init {
       // viewModelScope.launch {
            getNews()

            //            result.forEach {
//                Log.d("News", it.title)
//            }
       // }
    }

    fun getNews( text:String?=null , country:String?=null) {
        job?.cancel()
        job = viewModelScope.launch {
            _state.tryEmit(Loading)
            try {
                val result = getNewsUseCase.invoke("sp", null, null)
                _state.tryEmit(State.Success(result))
            } catch (e: Exception) {
                _state.tryEmit(Error(e.message.toString()))
            }
        }
    }
}

