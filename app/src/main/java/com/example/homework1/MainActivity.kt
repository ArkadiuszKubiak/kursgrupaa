package com.example.homework1

import android.app.Activity
import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import android.view.View
import android.widget.TextView



const val REQUEST_ID = 1
const val COUNTER_ID = "Counter"

class MainActivity : AppCompatActivity() {

    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_ID && resultCode == Activity.RESULT_OK)
        {
            if (data != null) {
                counter = data.getIntExtra(COUNTER_ID,0)
            }
        }
    }

    fun StartSecondActivity(@Suppress("UNUSED_PARAMETER")view: View?)
    {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(COUNTER_ID, counter)
        startActivityForResult(intent, REQUEST_ID)
    }

}


