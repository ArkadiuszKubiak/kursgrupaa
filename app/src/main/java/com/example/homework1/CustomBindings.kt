package com.example.homework1

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import java.io.InputStream
import java.net.URL
import java.util.*


object MyBindingAdapters {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun bindRecyclerViewAdapter(imageView: ImageView, url: String) {

        Picasso.get().load(url).into(imageView)

    }
}
