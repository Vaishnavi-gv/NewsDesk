package com.example.newsdesk.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {

    @TypeConverter
    fun fromString(value:String):List<String>{   //converting string into list
        val gson= Gson()
        val listType=object: TypeToken<List<String>>() {}.type
        return gson.fromJson(value, listType)
    }


    @TypeConverter
    fun fromList(list:List<String>):String  //converting list into json in string form
    {
        val gson=Gson()
        return gson.toJson(list)
    }
}