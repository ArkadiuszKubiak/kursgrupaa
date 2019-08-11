package com.example.homework1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.second_activity.*
import java.lang.NumberFormatException

class SecondActivity : AppCompatActivity() {
    private lateinit var counterTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        this.counterTextView = findViewById(R.id.cntTextViewSecond)

        // If it's the first time it happens, it should be null => otherwise it's not gonna be null.
        if (savedInstanceState == null) {
            this.counterTextView.text = intent.getIntExtra(COUNTER_ID, 0).toString()
        } else {
            savedInstanceState.getInt(COUNTER_ID)
        }


        buttonFragment.setOnClickListener {

            // Get the support fragment manager instance
            val manager = supportFragmentManager

            // Begin the fragment transition using support fragment manager
            val transaction = manager.beginTransaction()

            val currentFragment = supportFragmentManager.findFragmentByTag("FirstFragment")

            // It's either SecondFragment or None.
            if (currentFragment == null) {
                // Get the text fragment instance
                val myFragment = FirstFragment()

                // Replace the fragment on container
                transaction.replace(R.id.fragment_container, myFragment, "FirstFragment")
            }
            else
            {
                // Get the text fragment instance
                val myFragment = SecondFragment()

                // Replace the fragment on container
                transaction.replace(R.id.fragment_container, myFragment, "SecondFragment")
            }

            transaction.addToBackStack(null)

            // Finishing the transition
            transaction.commit()
        }


    }

    fun startFirstActivity(@Suppress("UNUSED_PARAMETER")view: View?)
    {
        val intent = Intent(this, MainActivity::class.java)
        try {
            val cntTextViewValue = this.counterTextView.text.toString()
            intent.putExtra(COUNTER_ID, cntTextViewValue.toInt()+1)
        } catch (e: NumberFormatException) {
            intent.putExtra(COUNTER_ID, 0)
        }

        startActivity(intent)
    }

}