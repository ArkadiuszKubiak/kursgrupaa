package com.example.homework1.course.views

import android.animation.ObjectAnimator
import android.os.Bundle
import android.widget.Toast
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

    enum class AnimateActions {
        ATTACK, CATCH, NEXT_POKEMON, SUCCESS, FAILURE
    }

    // ToDo: Better messages for Toasts (they are now closed by each other)
    // ToDo: Better nulls and bad values handling.
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        loginName = intent.getStringExtra("LOGIN")!!

        binding = DataBindingUtil.setContentView(this, R.layout.catching_pokemons)
        model = ViewModelProviders.of(this, MyViewModelFactory(this.application)).get(PokemonCatchingViewModel::class.java)

        model.initViewModel(loginName)

        binding.model = model
        binding.lifecycleOwner = this

        model.currentTrainerData.observe(this, Observer { it ->
            run {
                if (it != null) {
                    Toast.makeText(applicationContext, "New trainer Pokemon: %s!".format(it.login), Toast.LENGTH_SHORT).show()
                }
            }
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
            run {
                if (it != null) {
                    Glide.with(this).load(it.pokemon_data.sprites.frontDefault).into(binding.wildPokemon)
                    Toast.makeText(applicationContext, "New wild Pokemon: %s!".format(it.name), Toast.LENGTH_SHORT).show()
                }

            }
        })

        binding.attackButton.setOnClickListener { view ->
            animateAction(AnimateActions.ATTACK)
            if (model.onAttack()) {
                Toast.makeText(
                    applicationContext,
                    "Attacked! Success! Chance to catch increased: %s.".format(model.currentChanceToCatchPokemon),
                    Toast.LENGTH_SHORT
                )
                    .show()
            } else {
                model.loadRandomWildPokemon()
                Toast.makeText(applicationContext, "Attacked! Failure! Pokemon ran away!", Toast.LENGTH_SHORT)
                    .show()
            }
        }


        binding.catchButton.setOnClickListener { view ->
            animateAction(AnimateActions.CATCH)
            if (model.tryToCatchPokemon()) {
                Toast.makeText(
                    applicationContext,
                    "Caught Pokemon %s with Chance to catch: %s.".format(model.currentWildPokemon.value!!.name, model.currentChanceToCatchPokemon),
                    Toast.LENGTH_SHORT

                ).show()
            } else {
                Toast.makeText(applicationContext, "Failed to catch. Pokemon ran.", Toast.LENGTH_SHORT).show()
            }
            model.loadRandomWildPokemon()
        }

        binding.nextPokemon.setOnClickListener { view ->
            animateAction(AnimateActions.NEXT_POKEMON)
            model.getRandomWildPokemon()
            Toast.makeText(applicationContext, "Going for the next wild Pokemon!.", Toast.LENGTH_SHORT).show()
        }

        binding.goBack.setOnClickListener { view ->
            Toast.makeText(applicationContext, "Going back.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    // ToDo: Better animation and actions.
    // ToDo: Make an animation class and synchronize actions.
    fun animateAction(action: AnimateActions) {
        when (action) {
            AnimateActions.ATTACK -> {
                Glide.with(this).load(resources.getDrawable(R.drawable.stone)).into(binding.terrainImage)
                throwAnimation()
            }
            AnimateActions.CATCH -> {
                Glide.with(this).load(resources.getDrawable(R.drawable.pokeball)).into(binding.terrainImage)
                throwAnimation()
            }
            AnimateActions.NEXT_POKEMON -> {
                Glide.with(this).load(resources.getDrawable(R.drawable.logo)).into(binding.terrainImage)
                rotateAnimation()
            }
            AnimateActions.SUCCESS -> {
                Glide.with(this).load(resources.getDrawable(R.drawable.success)).into(binding.terrainImage)
                resizeAnimation()
            }
            AnimateActions.FAILURE -> {
                Glide.with(this).load(resources.getDrawable(R.drawable.failure)).into(binding.terrainImage)
                rotateAnimation()
            }
        }


    }

    fun rotateAnimation() {
        val aniView = binding.terrainImage
        if (aniView.rotation == 360.0f) {
            val animation1 = ObjectAnimator.ofFloat(
                aniView,
                "rotation", 0.0f
            )
            animation1.duration = 2000
            animation1.start()
        } else {
            val animation1 = ObjectAnimator.ofFloat(
                aniView,
                "rotation", 360.0f
            )
            animation1.duration = 2000
            animation1.start()
        }
    }

    fun resizeAnimation() {
        val aniView = binding.terrainImage
        val animation1 = ObjectAnimator.ofFloat(
            aniView,
            "scaleX", 2.0f
        )
        val animation2 = ObjectAnimator.ofFloat(
            aniView,
            "scaleY", 2.0f
        )
        animation1.duration = 2000
        animation2.duration = 2000
        animation1.start()
        animation2.start()
    }

    fun throwAnimation() {
        val aniView = binding.terrainImage
        val animation1 = ObjectAnimator.ofFloat(
            aniView,
            "translationY", binding.pokemonImage.y, binding.wildPokemon.y
        )
        animation1.duration = 2000
        animation1.start()

    }

}