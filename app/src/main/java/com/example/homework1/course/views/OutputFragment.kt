package com.example.homework1.course.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.homework1.R
import com.example.homework1.course.viewmodels.MyViewModelFactory
import com.example.homework1.course.viewmodels.PokeDexViewModel
import kotlinx.android.synthetic.main.fragment_output.*

class OutputFragment : Fragment() {

    lateinit var viewModel: PokeDexViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.run {
            ViewModelProviders.of(this, MyViewModelFactory(this.application)).get(PokeDexViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_output, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.selectedPokemon.observe(this, Observer {
            it?.let {
                pokemon_name.text = it.name.capitalize()
                pokemon_height_weight.text = "H:%s, W:%s".format(it.pokemon_data.height, it.pokemon_data.weight)
                pokemon_stats.text =
                    "Stats: " + it.pokemon_data.stats.joinToString { statData -> "%s=%s".format(statData.stat.name, statData.baseStat) }
                pokemon_abilities.text = "Abilities: " + it.pokemon_data.abilities.joinToString { ability -> "%s".format(ability.ability.name) }
                pokemon_types.text = "Types: " + it.pokemon_data.types.joinToString { type -> "%s".format(type.type.name) }

                context?.let { it1 -> Glide.with(it1).load(it.pokemon_data.sprites.frontDefault).into(pokemon_Image_front) }
                context?.let { it1 -> Glide.with(it1).load(it.pokemon_data.sprites.backDefault).into(pokemon_Image_back) }
                context?.let { it1 -> Glide.with(it1).load(it.pokemon_data.sprites.frontShiny).into(pokemon_Image_front_shiny) }
            }

        })

        viewModel.selectedPokemonOwnedData.observe(this, Observer {
            it?.let {
                pokemon_owned_by.text = "Owned by: " + it.joinToString { data -> data.pokedex_login }
                pokemon_owned_count.text = "Total owned count: " + it.size.toString()
            }
        })
    }


}