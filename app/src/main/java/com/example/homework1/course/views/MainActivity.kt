package com.example.homework1.course.views

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homework1.R
import com.example.homework1.course.database.AppDatabase
import com.example.homework1.course.database.PokemonRecord
import com.example.homework1.course.database.SynchData
import com.example.homework1.course.models.PokemonAll
import com.example.homework1.course.models.PokemonData
import com.example.homework1.course.rest_api.ApiClient
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private val CREATE_NEW_USER = 1

    companion object {
        const val DELETE_TIMEOUT_SECONDS = 60 * 60
        const val POKEMON_TO_DOWNLOAD = 200
        const val POKEMON_OFFSET = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        populateDatabase()
    }

    fun onCreatedCallback() {
        Thread(Runnable { Toast.makeText(applicationContext, "Stuff Loaded!", Toast.LENGTH_LONG).show() }).start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // 1
        if (requestCode == CREATE_NEW_USER) {
            // 2
            if (resultCode == Activity.RESULT_OK) {
                // 3
                val task = data?.getStringExtra(CreateNewUserView.CREATE_NEW_USER_DESCRIPTION)

                Log.d("arek", "" + task.toString())
                val toast = Toast.makeText(applicationContext, task.toString(), Toast.LENGTH_SHORT)

                toast.show()

            }
        }
    }

    fun createNewUser(view: View) {
        val intent = Intent(this, CreateNewUserView::class.java)
        startActivityForResult(intent, CREATE_NEW_USER)
    }

    fun loginInto(view: View) {
        val intent = Intent(this, PokemonView::class.java)
        startActivity(intent)
    }


    fun populateDatabase() {
        val getAllPokemonsCall: Call<PokemonAll> = ApiClient.getClient.getPokemons(
            offset = POKEMON_OFFSET,
            limit = POKEMON_TO_DOWNLOAD
        )

        getAllPokemonsCall.enqueue(object : Callback<PokemonAll> {
            override fun onResponse(call: Call<PokemonAll>?, response: Response<PokemonAll>?) {
                doAsync {

                    val database = AppDatabase.getInstance(context = this@MainActivity)

                    // Delete older items than the specified timeout.
                    val currentTimestamp = System.currentTimeMillis() / 1000
                    database.pokemonDao().deleteOlderDataThan(currentTimestamp - DELETE_TIMEOUT_SECONDS)

                    // Because then it will download them.
                    var xd = database.pokemonDao().getAllPokemonsNormal()
                    val allPokemons = database.pokemonDao().getAllPokemons()
                    if (allPokemons.value == null || allPokemons.value?.size!! < POKEMON_TO_DOWNLOAD) {
                        for (pokemonName in response!!.body()!!.results) {
                            val pokemonDataCall = ApiClient.getClient.getPokemonData(pokemonName.name)
                            val pokemonData = (pokemonDataCall.execute() as Response<PokemonData>).body()

                            val pokemon = PokemonRecord(
                                num = pokemonData!!.order,
                                name = pokemonName.name,
                                pokemon_data = pokemonData
                            )

                            val synchData = SynchData(
                                poke_name = pokemonData.name,
                                timestamp_seconds = currentTimestamp
                            )

                            Log.d("arek", "" + pokemon.name)

                            database.pokemonDao().insertPokemon(pokemon)
                            database.pokemonDao().insertSynchData(synchData)
                        }
                    }
                    onCreatedCallback()
                }
            }

            override fun onFailure(call: Call<PokemonAll>?, t: Throwable?) {
                Log.d("arek", "FAIL")
            }
        })
    }

}
