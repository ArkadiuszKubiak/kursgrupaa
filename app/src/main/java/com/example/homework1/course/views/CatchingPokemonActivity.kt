package com.example.homework1.course.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
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

        binding = DataBindingUtil.setContentView(this, R.layout.catching_pokemons)

        loginName = intent.getStringExtra("LOGIN")!!

        model = ViewModelProviders.of(this, MyViewModelFactory(this.application)).get(PokemonCatchingViewModel::class.java)

        /*model.currentTrainerPokemon.value = PokemonRecord(
            -1, "EMPTY",
            PokemonData(
                listOf(Ability(AbilityX("a", ""))),
                listOf(Form("", "")),
                1,
                1,
                "a",
                5,
                Sprites(""),
                listOf(Stat(5, StatX(""))),
                listOf(Type(TypeX(""))),
                5
            )
        )
        model.currentWildPokemon.value = PokemonRecord(
            -1, "EMPTY",
            PokemonData(
                listOf(Ability(AbilityX("a", ""))),
                listOf(Form("", "")),
                1,
                1,
                "a",
                5,
                Sprites(""),
                listOf(Stat(5, StatX(""))),
                listOf(Type(TypeX(""))),
                5
            )
        )*/




        model.setAndLoadTrainerData(loginName)
        model.getRandomWildPokemon()

        model.loadCurrentPokemonsTrainer()

        binding.model = model
        //binding.setVariable(BR.model,model)
        binding.lifecycleOwner = this

        model.currentWildPokemon.observe(this, Observer { it ->
            {}
        })

        model.currentTrainerData.observe(this, Observer { it ->
            {}
        })

        model.trainerPokemons.observe(this, Observer { it ->
            run {
                if (it != null) {
                    model.currentTrainerPokemon = model.getPokemonByName(it[0].name)
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