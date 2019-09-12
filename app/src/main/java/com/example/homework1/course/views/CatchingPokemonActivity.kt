package com.example.homework1.course.views

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.homework1.R
import com.example.homework1.course.viewmodels.MyViewModelFactory
import com.example.homework1.course.viewmodels.PokemonCatchingViewModel
import kotlinx.android.synthetic.main.catching_pokemons.*

class CatchingPokemonActivity : AppCompatActivity() {

    private lateinit var model: PokemonCatchingViewModel

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.create_new_user_view)

        model = ViewModelProviders.of(this, MyViewModelFactory(this.application)).get(PokemonCatchingViewModel::class.java)

        model.currentWildPokemon.observe(this, Observer { it ->
            {}
        })

        model.currentTrainerData.observe(this, Observer { it ->
            {}
        })

        model.trainerPokemons.observe(this, Observer { it ->
            {}
        })

        model.currentWildPokemon.observe(this, Observer { it ->
            {}
        })

        attackButton.setOnClickListener { view ->
            model.onAttack()
        }


        catchButton.setOnClickListener { view ->
            model.tryToCatchPokemon()
        }

        nextPokemon.setOnClickListener { view ->
            model.getRandomWildPokemon()
        }

        goBack.setOnClickListener { view ->
            // ToDo
        }
    }
}