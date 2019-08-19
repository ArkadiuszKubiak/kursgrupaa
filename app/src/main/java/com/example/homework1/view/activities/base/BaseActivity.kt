package com.example.homework1.view.activities.base

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("Registered")
abstract class BaseActivity : AppCompatActivity() {


    override fun onSaveInstanceState(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            super.onSaveInstanceState(savedInstanceState)
            try {
                // savedInstanceState.putInt(COUNTER_ID, this.counter)
            } catch (e: NumberFormatException) {
                // savedInstanceState.putInt(COUNTER_ID, 0)
            }

        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            // counter = savedInstanceState.getInt(COUNTER_ID)
            super.onRestoreInstanceState(savedInstanceState)
        }
    }


    protected fun <T> startActivityAndPassData(@Suppress("UNUSED_PARAMETER") view: View?, activityClass: Class<T>) {
        val intent = Intent(this, activityClass)
        try {
            // intent.putExtra(COUNTER_ID, counter + 1)
        } catch (e: NumberFormatException) {
            // intent.putExtra(COUNTER_ID, 0)
        }

        startActivity(intent)
    }

}
