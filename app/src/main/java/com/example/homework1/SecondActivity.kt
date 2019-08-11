package com.example.homework1

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.second_activity.*

class SecondActivity : BaseCountingActivity() {
    public lateinit var currentFragment: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        this.loadCounterValues(savedInstanceState, R.id.cntTextView)

        // Add an initial fragment.
        this.currentFragment = "FirstFragment"
        val myFragment = FirstFragment.newInstance(counterTextView.text.toString().toInt())
        addFragment(myFragment, R.id.fragment_container)


        // For switching fragments.
        buttonFragment.setOnClickListener {
            val myNewFragment: Fragment

            if (this.currentFragment != "FirstFragment") {
                myNewFragment = FirstFragment.newInstance(counterTextView.text.toString().toInt())
                currentFragment = "FirstFragment"
            } else {
                myNewFragment = SecondFragment.newInstance()
                currentFragment = "SecondFragment"
            }
            replaceFragment(myNewFragment, R.id.fragment_container)
        }
    }

    fun startActivityAndPassCounter(@Suppress("UNUSED_PARAMETER") view: View?) {
        this.startActivityAndPassCounter(view, MainActivity::class.java)
    }
}