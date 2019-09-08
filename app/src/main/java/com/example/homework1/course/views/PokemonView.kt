package com.example.homework1.course.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.homework1.R

class PokemonView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_view)

        supportFragmentManager.beginTransaction().add(
            R.id.layout_top,
            InputFragment()
        ).commit()

        supportFragmentManager.beginTransaction().add(
            R.id.layout_bottom,
            OutputFragment()
        ).commit()
    }

}