package com.example.homework1.course.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.homework1.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
