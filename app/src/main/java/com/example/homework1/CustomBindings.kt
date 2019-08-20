package com.example.homework1

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso


object MyBindingAdapters {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun bindRecyclerViewAdapter(imageView: ImageView, url: String) {

        Picasso.get().load(url).into(imageView)

    }

    @BindingAdapter("mark_active")
    @JvmStatic
    fun markActive(myView: View, isActive: Boolean) {
        if (isActive) {
            myView.setBackgroundColor(myView.context.resources.getColor(R.color.activeField))
        } else {
            myView.setBackgroundColor(myView.context.resources.getColor(R.color.inactiveField))
        }
    }

}
