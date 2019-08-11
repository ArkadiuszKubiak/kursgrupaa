package com.example.homework1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("Registered")
open class BaseCountingActivity : AppCompatActivity() {

    private lateinit var counterTextView: TextView

    var counter: Int
        get() = counterTextView.text.toString().toInt()
        protected set(value) {
            this.counterTextView.text = value.toString()
        }

    fun loadCounterValues(savedInstanceState: Bundle?, @androidx.annotation.IdRes id: Int) {
        this.counterTextView = findViewById(id)

        // If it's the first time it happens, it should be null => otherwise it's not gonna be null.
        if (savedInstanceState == null) {
            this.counter = intent.getIntExtra(COUNTER_ID, 0)
        } else {
            savedInstanceState.getInt(COUNTER_ID)
        }
    }


    override fun onSaveInstanceState(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            super.onSaveInstanceState(savedInstanceState)
            try {
                savedInstanceState.putInt(COUNTER_ID, this.counter)
            } catch (e: NumberFormatException) {
                savedInstanceState.putInt(COUNTER_ID, 0)
            }

        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt(COUNTER_ID)
            super.onRestoreInstanceState(savedInstanceState)
        }
    }


    protected fun <T> startActivityAndPassCounter(@Suppress("UNUSED_PARAMETER") view: View?, activityClass: Class<T>) {
        val intent = Intent(this, activityClass)
        try {
            intent.putExtra(COUNTER_ID, counter + 1)
        } catch (e: NumberFormatException) {
            intent.putExtra(COUNTER_ID, 0)
        }

        startActivity(intent)
    }

}
