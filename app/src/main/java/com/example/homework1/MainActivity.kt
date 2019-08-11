package com.example.homework1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView


const val COUNTER_ID = "Counter"

class MainActivity : BaseCountingActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.loadCounterValues(savedInstanceState, R.id.cntTextView)
    }

    fun startActivityAndPassCounter(@Suppress("UNUSED_PARAMETER") view: View?) {
        this.startActivityAndPassCounter(view, SecondActivity::class.java)
    }

}
