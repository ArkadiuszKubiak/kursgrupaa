package com.example.homework1.course.utilities

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object MyBindingAdapters {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun bindRecyclerViewAdapter(imageView: ImageView, url: String) {
        Glide.with(imageView.context).load(url).into(imageView)

    }
}