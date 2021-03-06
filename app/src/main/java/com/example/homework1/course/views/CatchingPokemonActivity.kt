package com.example.homework1.course.views

import android.animation.Animator
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.widget.ImageView
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

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        loginName = intent.getStringExtra(CreateNewUserView.CREATE_NEW_USER_DESCRIPTION_LOGIN_TEXT)!!

        val ab = supportActionBar
        ab!!.title = getString(R.string.trainer_name_title).format(loginName)

        binding = DataBindingUtil.setContentView(this, R.layout.catching_pokemons)
        model = ViewModelProviders.of(this, MyViewModelFactory(this.application)).get(PokemonCatchingViewModel::class.java)

        model.initViewModel(loginName)

        binding.model = model
        binding.lifecycleOwner = this

        model.currentTrainerData.observe(this, Observer { it ->
            run {
                if (it != null) {
                    //Toast.makeText(applicationContext, "New trainer Pokemon: %s!".format(it.login), Toast.LENGTH_SHORT).show()
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
                    //Toast.makeText(applicationContext, "New wild Pokemon: %s!".format(it.name), Toast.LENGTH_SHORT).show()
                }

            }
        })

        binding.attackButton.setOnClickListener { view ->
            model.interactionEnabled.value = false
            loadImage(AnimateActions.ATTACK)

            doAnimation(
                AnimationsDifferent.THROW,
                { Toast.makeText(applicationContext, getString(R.string.toast_attack), Toast.LENGTH_SHORT).show() },
                {
                    if (model.onAttack()) {
                        Toast.makeText(
                            applicationContext,
                            getString(R.string.toast_attack_success).format(model.currentChanceToCatchPokemon),
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    } else {
                        model.loadRandomWildPokemon()
                        Toast.makeText(applicationContext, getString(R.string.toast_attack_failure), Toast.LENGTH_SHORT)
                            .show()
                    }
                    model.interactionEnabled.value = true
                },
                binding.terrainImage
            )
        }


        binding.catchButton.setOnClickListener { view ->
            model.interactionEnabled.value = false
            loadImage(AnimateActions.CATCH)
            doAnimation(
                AnimationsDifferent.THROW,
                { Toast.makeText(applicationContext, getString(R.string.toast_pokeball_throw), Toast.LENGTH_SHORT).show() },
                {
                    if (model.tryToCatchPokemon()) {
                        Toast.makeText(
                            applicationContext,
                            getString(R.string.toast_success_catch).format(
                                model.currentWildPokemon.value!!.name,
                                model.currentChanceToCatchPokemon
                            ),
                            Toast.LENGTH_SHORT

                        ).show()
                        loadImage(AnimateActions.SUCCESS)
                        doAnimation(AnimationsDifferent.RESIZE)

                        loadImage(AnimateActions.SUCCESS)
                        doAnimation(AnimationsDifferent.RESIZE, null, { model.interactionEnabled.value = true })
                    } else {
                        Toast.makeText(applicationContext, getString(R.string.toast_failed_catch), Toast.LENGTH_SHORT).show()
                        loadImage(AnimateActions.FAILURE)
                        doAnimation(AnimationsDifferent.RESIZE, null, { model.interactionEnabled.value = true })
                    }
                    model.loadRandomWildPokemon()
                },
                binding.terrainImage,
                false
            )
        }

        binding.nextPokemon.setOnClickListener { view ->
            model.interactionEnabled.value = false
            loadImage(AnimateActions.NEXT_POKEMON)
            doAnimation(
                AnimationsDifferent.ROTATE,
                { Toast.makeText(applicationContext, getString(R.string.toast_next_pokemon), Toast.LENGTH_SHORT).show() },
                {
                    model.loadRandomWildPokemon()
                    model.interactionEnabled.value = true
                },
                binding.wildPokemon,
                false
            )
        }

        binding.goBack.setOnClickListener { view ->
            Toast.makeText(applicationContext, getString(R.string.toast_going_back), Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    // ToDo: Better animation and actions.
    // ToDo: Make an animation class and synchronize actions.
    fun loadImage(action: AnimateActions) {
        when (action) {
            AnimateActions.ATTACK -> {
                Glide.with(this).load(resources.getDrawable(R.drawable.myrock)).into(binding.terrainImage)
            }
            AnimateActions.CATCH -> {
                Glide.with(this).load(resources.getDrawable(R.drawable.pokeball_png)).into(binding.terrainImage)
            }
            AnimateActions.NEXT_POKEMON -> {
                // None
            }
            AnimateActions.SUCCESS -> {
                Glide.with(this).load(resources.getDrawable(R.drawable.caught)).into(binding.terrainImage)
            }
            AnimateActions.FAILURE -> {
                Glide.with(this).load(resources.getDrawable(R.drawable.fail)).into(binding.terrainImage)
            }
        }
    }

    enum class AnimationsDifferent {
        ROTATE, RESIZE, THROW
    }

    fun restore(viewToAnimate: ImageView = binding.terrainImage) {
        viewToAnimate.visibility = View.INVISIBLE
    }

    fun doAnimation(
        actionAnimation: AnimationsDifferent,
        animationStartCallback: (() -> Unit)? = null,
        animationEndCallback: (() -> Unit)? = null,
        viewToAnimate: ImageView = binding.terrainImage,
        doCleanUp: Boolean = true
    ) {

        viewToAnimate.visibility = View.VISIBLE

        val animation: ObjectAnimator

        when (actionAnimation) {
            AnimationsDifferent.ROTATE -> {
                animation = if (viewToAnimate.rotation == 360.0f) {
                    ObjectAnimator.ofFloat(
                        viewToAnimate,
                        "rotation", 0.0f
                    )
                } else {
                    ObjectAnimator.ofFloat(
                        viewToAnimate,
                        "rotation", 360.0f
                    )
                }
            }

            AnimationsDifferent.RESIZE -> {
                animation = ObjectAnimator.ofFloat(
                    viewToAnimate,
                    "scaleX", 2.0f
                )

            }

            AnimationsDifferent.THROW -> {
                animation = ObjectAnimator.ofFloat(
                    viewToAnimate,
                    "translationY", binding.pokemonImage.y + binding.pokemonImage.height, binding.wildPokemon.y - binding.wildPokemon.height
                )

            }
        }


        animation.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {
                // Nothing.
            }

            override fun onAnimationEnd(p0: Animator?) {
                animationEndCallback?.let { animationEndCallback() }

                // Visibility clean-up
                if (doCleanUp) {
                    restore(viewToAnimate)
                }

                // Restore old options.
                when (actionAnimation) {
                    AnimationsDifferent.ROTATE -> {
                    }
                    AnimationsDifferent.RESIZE -> {
                        viewToAnimate.scaleX = 1.0f
                    }
                    AnimationsDifferent.THROW -> {
                    }
                }
            }

            override fun onAnimationCancel(p0: Animator?) {
                // Nothing
            }

            override fun onAnimationStart(p0: Animator?) {
                animationStartCallback?.let { animationStartCallback() }
            }
        })
        animation.duration = 2500
        animation.start()
    }
}

