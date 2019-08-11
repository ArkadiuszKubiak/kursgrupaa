package com.example.homework1

import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.second_activity.*

class SecondActivity : BaseCountingActivity() {
    lateinit var currentFragment: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        this.loadCounterValues(savedInstanceState, R.id.cntTextView)

        // Add an initial fragment.
        this.currentFragment = "FirstFragment"
        val myFragment = FirstFragment.newInstance(this.counter)
        addFragment(myFragment, R.id.fragment_container)

        // For switching activity.
        buttonActivityReturn.setOnClickListener {
            this.startActivityAndPassCounter(it, MainActivity::class.java)
        }

        // For switching fragments.
        buttonFragment.setOnClickListener {
            val myNewFragment: Fragment

            if (this.currentFragment != "FirstFragment") {
                myNewFragment = FirstFragment.newInstance(this.counter)
                this.currentFragment = "FirstFragment"
            } else {
                myNewFragment = SecondFragment.newInstance()
                this.currentFragment = "SecondFragment"
            }
            replaceFragment(myNewFragment, R.id.fragment_container)
        }
    }

}