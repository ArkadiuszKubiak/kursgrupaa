package com.example.homework1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.layout_top, InputFragment()).commit()
        supportFragmentManager.beginTransaction().add(R.id.layout_bottom, OutputFragment()).commit()


    }
}
