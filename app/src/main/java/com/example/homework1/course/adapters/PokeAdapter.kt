package com.example.homework1.course.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.homework1.R
import com.example.homework1.course.database.PokemonRecord


class PokeAdapter(private var activity: Activity, private val pokemon_data: MutableList<PokemonRecord>) :
    BaseAdapter() {

    var currentIndex: Int = 0

    fun reloadPokemonData(pokeData: List<PokemonRecord>?) {
        pokemon_data.clear()
        currentIndex = 0
        pokeData?.let { pokemon_data.addAll(it) }
        notifyDataSetChanged()
    }

    private class ViewHolder(row: View?) {
        var pokemonIndex: TextView? = null
        var pokemonName: TextView? = null

        init {
            pokemonIndex = row?.findViewById<TextView>(R.id.pokemonIndex)
            pokemonName = row?.findViewById<TextView>(R.id.pokemonName)
        }
    }

    @SuppressLint("InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View?
        val viewHolder: ViewHolder

        if (convertView == null) {
            val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.list_view, null)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val userDto = pokemon_data[position]
        currentIndex = position
        viewHolder.pokemonName?.text = userDto.name.capitalize()
        viewHolder.pokemonIndex?.text = "$currentIndex. "


        return view as View
    }

    override fun getItem(i: Int): PokemonRecord {
        return pokemon_data[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return pokemon_data.size
    }

}