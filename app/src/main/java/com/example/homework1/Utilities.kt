package com.example.homework1

import android.content.res.AssetManager
import android.graphics.drawable.Drawable
import java.io.InputStream
import java.net.URL


fun readJsonFromAssets(assets: AssetManager, filePath: String): String {
    try {
        val inputStream: InputStream = assets.open(filePath)

        return inputStream.bufferedReader().use { it.readText() }
        //Log.d(TAG,inputString)
    } catch (e: Exception) {
        //Log.d(TAG, e.toString())
    }

    return ""
}

private fun GetImg(url: String): Drawable? {
    val inputStream = URL(url).content as InputStream
    return Drawable.createFromStream(inputStream, "src name")
}