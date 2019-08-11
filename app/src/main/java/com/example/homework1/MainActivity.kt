package com.example.homework1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


const val COUNTER_ID = "Counter"

class MainActivity : AppCompatActivity() {

    private lateinit var counterTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.counterTextView = findViewById(R.id.cntTextView)

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

    fun startSecondActivity(@Suppress("UNUSED_PARAMETER") view: View?) {
        val intent = Intent(this, SecondActivity::class.java)
        try {
            val cntTextViewValue = this.counterTextView.text.toString()
            intent.putExtra(COUNTER_ID, cntTextViewValue.toInt() + 1)
        } catch (e: NumberFormatException) {
            intent.putExtra(COUNTER_ID, 0)
        }

        startActivity(intent)
    }

}
