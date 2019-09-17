package com.example.homework1.course.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.homework1.R
import com.example.homework1.course.viewmodels.MyViewModelFactory
import com.example.homework1.course.viewmodels.PokeDexViewModel

class PokemonView : AppCompatActivity() {

    var loginName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_view)

        loginName = intent.getStringExtra(CreateNewUserView.CREATE_NEW_USER_DESCRIPTION_LOGIN_TEXT)!!

        val model = ViewModelProviders.of(this, MyViewModelFactory(this.application)).get(PokeDexViewModel::class.java)

        supportFragmentManager.beginTransaction().add(
            R.id.layout_top,
            InputFragment.newInstance(loginName)
        ).commit()

        supportFragmentManager.beginTransaction().add(
            R.id.layout_bottom,
            OutputFragment()
        ).commit()
    }

    fun goCatchPokemons(view: View) {
        val intent = Intent(this, CatchingPokemonActivity::class.java)
        intent.putExtra(CreateNewUserView.CREATE_NEW_USER_DESCRIPTION_LOGIN_TEXT, loginName)
        startActivity(intent)
    }

}