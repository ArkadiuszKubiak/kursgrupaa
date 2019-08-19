package com.example.homework1

import android.content.res.AssetManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import org.json.JSONObject
import java.io.InputStream

fun readJsonFromAssets(assets : AssetManager, filePath: String) :JSONObject
{
    try {
        val inputStream: InputStream = assets.open(filePath)
        val inputString = inputStream.bufferedReader().use{it.readText()}
        return JSONObject(inputString)
        //Log.d(TAG,inputString)
    } catch (e:Exception){
        //Log.d(TAG, e.toString())
    }

    return JSONObject()
}