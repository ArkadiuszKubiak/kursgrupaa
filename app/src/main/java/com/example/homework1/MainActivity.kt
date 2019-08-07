package com.example.homework1

import android.app.Activity
import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import android.view.View
import android.widget.TextView



const val REQUEST_ID = 1
const val COUNTER_ID = "Counter"
const val TEXT_CNT = "Second activity counter: "

class MainActivity : AppCompatActivity() {

    lateinit var CounterTextView :TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CounterTextView = findViewById(R.id.cntTextView) as TextView
        CounterTextView.setText(getString(R.string.Default_textView))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_ID && resultCode == Activity.RESULT_OK)
        {
            if (data != null) {
                val value = data.getIntExtra(COUNTER_ID,0)
                CounterTextView.setText(getString(R.string.Default_text_content, value))
            }
        }
    }

    fun StartSecondActivity(@Suppress("UNUSED_PARAMETER")view: View?)
    {
        val intent = Intent(this, SecondActivity::class.java)
        val cntTextView = CounterTextView.text.toString()
        val pattern = "\\d+".toRegex()
        val found = pattern.find(cntTextView)
        if(found != null) {
            intent.putExtra(COUNTER_ID, found.value.toInt())
        }
        else {
            intent.putExtra(COUNTER_ID, 0)
        }

        startActivityForResult(intent, REQUEST_ID)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        if(outState != null) {
            super.onSaveInstanceState(outState)
            val cntTextView = CounterTextView.text.toString()
            val pattern = "\\d+".toRegex()
            val found = pattern.find(cntTextView)
            if (found != null) {
                outState.putInt(COUNTER_ID, found.value.toInt())
            } else {
                outState.putInt(COUNTER_ID, 0)
            }
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        if(savedInstanceState != null) {
            super.onRestoreInstanceState(savedInstanceState)
                val value = savedInstanceState.getInt(COUNTER_ID)
                CounterTextView.setText(getString(R.string.Default_text_content, value))

        }
    }
}
