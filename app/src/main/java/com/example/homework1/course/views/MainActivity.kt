package com.example.homework1.course.views

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PowerManager
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.homework1.R
import com.example.homework1.course.database.AppDatabase
import com.example.homework1.course.database.PokemonRecord
import com.example.homework1.course.database.SynchData
import com.example.homework1.course.models.PokemonData
import com.example.homework1.course.models.PokemonPokedex
import com.example.homework1.course.rest_api.ApiClient
import com.example.homework1.course.utilities.TAG
import com.example.homework1.course.viewmodels.MyViewModelFactory
import com.example.homework1.course.viewmodels.UserCreationViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {


    lateinit var progressProgressDialog: ProgressDialog
    private lateinit var model: UserCreationViewModel

    private var pendingCreation: Boolean = false

    companion object {
        private const val DELETE_TIMEOUT_SECONDS = 3600
        private const val REGION = "kanto"
        private const val CREATE_NEW_USER = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        populateDatabase()
        Thread.sleep(1500)
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        model = ViewModelProviders.of(this, MyViewModelFactory(this.application)).get(UserCreationViewModel::class.java)
        progressProgressDialog = ProgressDialog(this)
        progressProgressDialog.setTitle(getString(R.string.loading_pokemon))
        progressProgressDialog.setCancelable(false)
        progressProgressDialog.show()
    }

    override fun onStart() {
        super.onStart()

        PasswordText.text.clear()
        LoginTest.text.clear()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val loginText: String?
        val surnameText: String?
        val nameText: String?
        val passwordText: String?

        if (requestCode == CREATE_NEW_USER) {
            // 2
            if (resultCode == Activity.RESULT_OK) {
                // 3
                loginText = data?.getStringExtra(CreateNewUserView.CREATE_NEW_USER_DESCRIPTION_LOGIN_TEXT)
                surnameText = data?.getStringExtra(CreateNewUserView.CREATE_NEW_USER_DESCRIPTION_SURNAME)
                nameText = data?.getStringExtra(CreateNewUserView.CREATE_NEW_USER_DESCRIPTION_NAME)
                passwordText = data?.getStringExtra(CreateNewUserView.CREATE_NEW_USER_DESCRIPTION_PASSWORD)

                if ((loginText != null) && (nameText != null) && (surnameText != null) && (passwordText != null)) {
                    pendingCreation = true
                    model.getTrainerByLogin(loginText).observe(this, Observer { trainerData ->
                        if (pendingCreation) {
                            trainerData?.let {
                                Toast.makeText(applicationContext, getString(R.string.toast_user_exists), Toast.LENGTH_SHORT).show()
                            } ?: doAsync {
                                model.createNewTrainer(loginText, nameText, surnameText, passwordText)
                                Toast.makeText(applicationContext, getString(R.string.toast_user_added).format(loginText), Toast.LENGTH_LONG).show()
                            }
                        }
                        pendingCreation = false
                    })
                } else {
                    Toast.makeText(applicationContext, getString(R.string.toast_empty_password), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    fun createNewUserActivity(view: View) {
        val intent = Intent(this, CreateNewUserView::class.java)
        intent.putExtra(CreateNewUserView.CREATE_NEW_USER_DESCRIPTION_LOGIN_TEXT, LoginTest.text.toString())
        startActivityForResult(intent, CREATE_NEW_USER)
    }


    fun startPokemonViewActivity(view: View) {
        if ((LoginTest.text.toString() != "") && ((PasswordText.text.toString() != ""))) {
            model.getTrainerByLogin(LoginTest.text.toString()).observe(this, Observer { trainerData ->
                trainerData?.let {
                    if (trainerData.password == PasswordText.text.toString()) {
                        val intent = Intent(this, PokemonView::class.java)
                        intent.putExtra(CreateNewUserView.CREATE_NEW_USER_DESCRIPTION_LOGIN_TEXT, LoginTest.text.toString())
                        startActivity(intent)
                    } else {
                        Toast.makeText(applicationContext, getString(R.string.toast_wrong_password), Toast.LENGTH_SHORT).show()
                    }
                } ?: Toast.makeText(applicationContext, getString(R.string.toast_user_not_exist), Toast.LENGTH_SHORT).show()
            })
        } else {
            Toast.makeText(applicationContext, getString(R.string.toast_missing_fields), Toast.LENGTH_LONG).show()
        }
    }

    private fun populateDatabase() {
        val getAllPokemonFromGivenRegion: Call<PokemonPokedex> = ApiClient.getClient.getPokedex(
            region = REGION
        )

        val wakeLock: PowerManager.WakeLock =
            (getSystemService(Context.POWER_SERVICE) as PowerManager).run {
                newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyApp::MyWakelockTag").apply {
                    acquire()
                }
            }

        getAllPokemonFromGivenRegion.enqueue(object : Callback<PokemonPokedex> {
            override fun onResponse(call: Call<PokemonPokedex>?, response: Response<PokemonPokedex>?) {
                doAsync {
                    try {
                        val database = AppDatabase.getInstance(context = this@MainActivity)

                        val pokemonEntries = response!!.body()!!.pokemonEntries

                        // Delete older items than the specified timeout.
                        val currentTimestamp = System.currentTimeMillis() / 1000
                        database.pokemonDao().deleteOlderDataThan(currentTimestamp - DELETE_TIMEOUT_SECONDS)

                        // Because then it will download them.
                        val pokemonDatabase = database.pokemonDao().getAllPokemonsNormal()



                        if (pokemonDatabase.size < pokemonEntries.size) {
                            for (pokeData in pokemonEntries) {
                                val pokemonName = pokeData.pokemonSpecies.name
                                val pokemonDataCall = ApiClient.getClient.getPokemonData(pokemonName)
                                val pokemonData = (pokemonDataCall.execute() as Response<PokemonData>).body()

                                val pokemon = PokemonRecord(
                                    num = pokemonData!!.order,
                                    name = pokemonName,
                                    pokemon_data = pokemonData
                                )

                                val synchData = SynchData(
                                    poke_name = pokemonData.name,
                                    timestamp_seconds = currentTimestamp
                                )

                                Log.d(TAG, "Successfully retrieved " + pokemon.name + "from PokeApi.")

                                database.pokemonDao().insertPokemon(pokemon)
                                database.pokemonDao().insertSynchData(synchData)
                            }
                        }
                        progressProgressDialog.dismiss()
                        wakeLock.release()
                    } catch (e: Exception) {
                        progressProgressDialog.dismiss()
                        wakeLock.release()
                        uiThread {
                            Toast.makeText(
                                applicationContext,
                                getString(R.string.toast_failed_loading_pokemons).format(e.message),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<PokemonPokedex>?, t: Throwable?) {
                Log.d(TAG, "Sadly, the call for getting Pokemons failed :(.")
                progressProgressDialog.dismiss()
                wakeLock.release()
                Toast.makeText(applicationContext, getString(R.string.toast_failed_loading_pokemons).format(t?.message), Toast.LENGTH_SHORT).show()
            }
        })
    }

}
