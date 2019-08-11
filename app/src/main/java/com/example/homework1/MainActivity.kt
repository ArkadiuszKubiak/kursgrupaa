package com.example.homework1

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


const val COUNTER_ID = "Counter"

class MainActivity : BaseCountingActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.loadCounterValues(savedInstanceState, R.id.cntTextView)

        // For switching activity.
        buttonActivityReturn.setOnClickListener {
            this.startActivityAndPassCounter(it, SecondActivity::class.java)
        }
    }
}
