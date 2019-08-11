package com.example.homework1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("Registered")
open class BaseCountingActivity : AppCompatActivity() {

    public lateinit var counterTextView: TextView

    fun loadCounterValues(savedInstanceState: Bundle?, @androidx.annotation.IdRes id: Int) {
        this.counterTextView = findViewById(id)

        // If it's the first time it happens, it should be null => otherwise it's not gonna be null.
        if (savedInstanceState == null) {
            this.counterTextView.text = intent.getIntExtra(COUNTER_ID, 0).toString()
        } else {
            savedInstanceState.getInt(COUNTER_ID)
        }
    }


    override fun onSaveInstanceState(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            super.onSaveInstanceState(savedInstanceState)
            try {
                val cntTextViewValue = counterTextView.text.toString()
                savedInstanceState.putInt(COUNTER_ID, cntTextViewValue.toInt())
            } catch (e: NumberFormatException) {
                savedInstanceState.putInt(COUNTER_ID, 0)
            }

        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            counterTextView.text = savedInstanceState.getInt(COUNTER_ID).toString()
            super.onRestoreInstanceState(savedInstanceState)
        }
    }


    protected fun <T> startActivityAndPassCounter(@Suppress("UNUSED_PARAMETER") view: View?, activityClass: Class<T>) {
        val intent = Intent(this, activityClass)
        try {
            val cntTextViewValue = this.counterTextView.text.toString()
            intent.putExtra(COUNTER_ID, cntTextViewValue.toInt() + 1)
        } catch (e: NumberFormatException) {
            intent.putExtra(COUNTER_ID, 0)
        }

        startActivity(intent)
    }

}
