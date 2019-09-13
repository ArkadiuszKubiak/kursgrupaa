package com.example.homework1.course.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.homework1.R
import com.example.homework1.course.viewmodels.MyViewModelFactory
import com.example.homework1.course.viewmodels.PokemonCatchingViewModel
import com.example.homework1.databinding.CatchingPokemonsBinding

class CatchingPokemonActivity : AppCompatActivity() {

    private lateinit var binding: CatchingPokemonsBinding
    private lateinit var loginName: String

    private lateinit var model: PokemonCatchingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        loginName = intent.getStringExtra("LOGIN")!!

        binding = DataBindingUtil.setContentView(this, R.layout.catching_pokemons)
        model = ViewModelProviders.of(this, MyViewModelFactory(this.application)).get(PokemonCatchingViewModel::class.java)

        model.initViewModel(loginName)

        binding.model = model
        binding.lifecycleOwner = this

        model.currentWildPokemon.observe(this, Observer { it ->
            if (it != null) {
                Glide.with(this).load(it.pokemon_data.sprites.frontDefault).into(binding.wildPokemon)
            }
        })

        model.currentTrainerData.observe(this, Observer { it ->
            {}
        })

        model.trainerPokemons.observe(this, Observer { it ->
            run {
                if (it != null && it.size > 0) {
                    model.currentTrainerPokemon = it[0]
                    Glide.with(this).load(model.currentTrainerPokemon!!.pokemon_data.sprites.frontDefault).into(binding.pokemonImage)
                }
            }
        })


        model.currentWildPokemon.observe(this, Observer { it ->
            {}
        })


        binding.attackButton.setOnClickListener { view ->
            model.onAttack()
        }


        binding.catchButton.setOnClickListener { view ->
            model.tryToCatchPokemon()
        }

        binding.nextPokemon.setOnClickListener { view ->
            model.getRandomWildPokemon()
        }

        binding.goBack.setOnClickListener { view ->
            // ToDo
        }
    }

}