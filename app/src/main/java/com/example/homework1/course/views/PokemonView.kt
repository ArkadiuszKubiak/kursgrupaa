package com.example.homework1.course.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.homework1.R
import com.example.homework1.course.viewmodels.PokeDexViewModel

class PokemonView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_view)

        val loginName = intent.getStringExtra("LOGIN")!!

        val model = ViewModelProviders.of(this).get(PokeDexViewModel::class.java)


        supportFragmentManager.beginTransaction().add(
            R.id.layout_top,
            InputFragment.newInstance(loginName)
        ).commit()

        supportFragmentManager.beginTransaction().add(
            R.id.layout_bottom,
            OutputFragment()
        ).commit()
    }

}