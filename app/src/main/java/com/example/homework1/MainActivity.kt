package com.example.homework1

import android.os.Bundle
import android.view.View


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
