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
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.homework1.R
import com.example.homework1.course.database.AppDatabase
import com.example.homework1.course.database.PokemonRecord
import com.example.homework1.course.database.SynchData
import com.example.homework1.course.models.PokemonAll
import com.example.homework1.course.models.PokemonData
import com.example.homework1.course.rest_api.ApiClient
import com.example.homework1.course.utilities.TAG
import com.example.homework1.course.viewmodels.MyViewModelFactory
import com.example.homework1.course.viewmodels.PokeDexViewModel
import com.example.homework1.course.views.MainActivity.Companion.POKEMON_OFFSET
import com.example.homework1.course.views.MainActivity.Companion.POKEMON_TO_DOWNLOAD
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private val CREATE_NEW_USER = 1

    lateinit var progerssProgressDialog: ProgressDialog
    private lateinit var model: PokeDexViewModel

    companion object {
        const val DELETE_TIMEOUT_SECONDS = 3600
        const val POKEMON_TO_DOWNLOAD = 151
        const val POKEMON_OFFSET = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        model = ViewModelProviders.of(this, MyViewModelFactory(this.application)).get(PokeDexViewModel::class.java)
        progerssProgressDialog = ProgressDialog(this)
        progerssProgressDialog.setTitle("Loading Pokemons")
        progerssProgressDialog.setCancelable(false)
        progerssProgressDialog.show()

        populateDatabase()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var loginText:String?=null
        var surnameText:String?=null
        var nameText:String?=null
        var passwordText:String?=null
        if (requestCode == CREATE_NEW_USER) {
            // 2
            if (resultCode == Activity.RESULT_OK) {
                // 3
                loginText = data?.getStringExtra(CreateNewUserView.CREATE_NEW_USER_DESCRIPTION_LOGIN_TEXT)
                surnameText = data?.getStringExtra(CreateNewUserView.CREATE_NEW_USER_DESCRIPTION_SURNAME)
                nameText = data?.getStringExtra(CreateNewUserView.CREATE_NEW_USER_DESCRIPTION_NAME)
                passwordText = data?.getStringExtra(CreateNewUserView.CREATE_NEW_USER_DESCRIPTION_PASSWORD)
                if ((loginText != null) && (nameText != null) && (surnameText != null) && (passwordText != null))
                {
                    model.getTrainerByName(loginText).observe(this, Observer { trainerData ->
                        trainerData?.let {
                            Toast.makeText(applicationContext, "User already exists!", Toast.LENGTH_SHORT).show()
                        } ?:
                        doAsync {
                            model.createNewTrainer(loginText, nameText, surnameText, passwordText)
                            Toast.makeText(applicationContext, "User added: %s".format(loginText), Toast.LENGTH_LONG).show()
                        }
                    })
                }
                else
                {
                    Toast.makeText(applicationContext, "User name or password is empty!", Toast.LENGTH_SHORT).show()
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

        if ((LoginTest.text.toString() != "") && ((PasswordText.text.toString() != "")))
        {
            model.getTrainerByName(LoginTest.text.toString()).observe(this, Observer { trainerData ->
                trainerData?.let {
                    if(trainerData.password == PasswordText.text.toString())
                    {
                        val intent = Intent(this, PokemonView::class.java)
                        intent.putExtra(CreateNewUserView.CREATE_NEW_USER_DESCRIPTION_LOGIN_TEXT, LoginTest.text.toString())
                        startActivity(intent)
                    }
                    else
                    {
                        Toast.makeText(applicationContext, "User not exists or wrong password!", Toast.LENGTH_SHORT).show()
                    }
                } ?: Toast.makeText(applicationContext, "User not exists or wrong password!", Toast.LENGTH_SHORT).show()
            })
        }
        else
        {
            Toast.makeText(applicationContext, "Wrong user name or password", Toast.LENGTH_LONG).show()
        }
    }

    fun populateDatabase() {
        val getAllPokemonsCall: Call<PokemonAll> = ApiClient.getClient.getPokemons(
            offset = POKEMON_OFFSET,
            limit = POKEMON_TO_DOWNLOAD
        )
        val wakeLock: PowerManager.WakeLock =
            (getSystemService(Context.POWER_SERVICE) as PowerManager).run {
                newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyApp::MyWakelockTag").apply {
                    acquire()
                }
            }

        getAllPokemonsCall.enqueue(object : Callback<PokemonAll> {
            override fun onResponse(call: Call<PokemonAll>?, response: Response<PokemonAll>?) {
                doAsync {

                    val database = AppDatabase.getInstance(context = this@MainActivity)

                    // Delete older items than the specified timeout.
                    val currentTimestamp = System.currentTimeMillis() / 1000
                    database.pokemonDao().deleteOlderDataThan(currentTimestamp - DELETE_TIMEOUT_SECONDS)

                    // Because then it will download them.
                    val pokemonDatabase = database.pokemonDao().getAllPokemonsNormal()

                    if (pokemonDatabase.size < POKEMON_TO_DOWNLOAD) {
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

                            Log.d(TAG, "Successfully retrieved " + pokemon.name + "from PokeApi.")

                            database.pokemonDao().insertPokemon(pokemon)
                            database.pokemonDao().insertSynchData(synchData)
                        }
                    }
                    progerssProgressDialog.dismiss()
                }
            }

            override fun onFailure(call: Call<PokemonAll>?, t: Throwable?) {
                Log.d(TAG, "Sadly, the call for getting Pokemons failed :(.")
            }
        })
        wakeLock.release()
    }

}
