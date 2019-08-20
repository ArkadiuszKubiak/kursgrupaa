package com.example.homework1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_output.*

class OutputFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val myView = LayoutInflater.from(container!!.context).inflate(R.layout.fragment_output,container,false)
        return myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val model = ViewModelProviders.of(activity!!).get(SharedViewModel::class.java)

        model.pokName.observe(this, Observer {
            it?.let {
                pokemon_name.text = model.pokName.value
            }
        })

        model.pokImg.observe(this, Observer {
            it?.let {
                context?.let { it1 -> Glide.with(it1).load(model.pokImg.value).into(pokemon_Image) }
            }
        })
    }


}