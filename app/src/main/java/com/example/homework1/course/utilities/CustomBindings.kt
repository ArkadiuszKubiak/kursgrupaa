package com.example.homework1.course.utilities

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object MyBindingAdapters {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun bindRecyclerViewAdapter(imageView: ImageView, url: String) {
        if (url != null) {
            Glide.with(imageView.context).load(url).into(imageView)
        }

    }
    @BindingAdapter("interactionEnabled")
    @JvmStatic
    fun manageInteraction(view: View, value: Boolean) {
        //view.visibility = if (value) View.VISIBLE else View.GONE
        view.isEnabled = value
    }
}