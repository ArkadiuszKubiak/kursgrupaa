package com.example.homework1

import android.content.res.AssetManager
import org.json.JSONObject
import java.io.InputStream
import android.graphics.drawable.Drawable
import java.net.URL


fun readJsonFromAssets(assets : AssetManager, filePath: String) : String
{
    try {
        val inputStream: InputStream = assets.open(filePath)

        return inputStream.bufferedReader().use{it.readText()}
        //Log.d(TAG,inputString)
    } catch (e:Exception){
        //Log.d(TAG, e.toString())
    }

    return ""
}

private fun GetImg(url: String): Drawable? {
    val inputStream = URL(url).getContent() as InputStream
    return Drawable.createFromStream(inputStream, "src name")
}