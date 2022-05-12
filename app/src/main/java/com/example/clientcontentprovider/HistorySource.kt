package com.example.clientcontentprovider

import android.content.ContentResolver
import android.content.ContentUris
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract


const val ID = "id"
const val NAME = "name"
const val TEMPERATURE = "temperature"
const val FEELS_LIKE = "feelsLike"
const val ICON = "icon"

class HistorySource(private val contentResolver: ContentResolver) {

    fun insert(entity: HistoryWeatherEntity){
       contentResolver.insert(HISTORY_URI,toContentValues(entity))
    }


    data class HistoryWeatherEntity(

        var id:Long=0,
        var name: String="",
        var temperature:Int=0,
        var feelsLike:Int=0,
        var icon:String="")

     val HISTORY_URI = Uri.parse("content://com.example.weathernew.EducationContentProvider/HistoryWeatherEntity")


    fun toEntity(cursor: Cursor): HistoryWeatherEntity{
        return HistoryWeatherEntity(
            cursor.getLong(cursor.getColumnIndex(ID)),
            cursor.getString(cursor.getColumnIndex(NAME)),
            cursor.getInt(cursor.getColumnIndex(TEMPERATURE))
        )
    }

    fun toContentValues(entity: HistoryWeatherEntity): ContentValues{
        return ContentValues().apply {
            put(ID,entity.id)
            put(NAME,entity.name)
            put(TEMPERATURE,entity.temperature)
        }
    }

}