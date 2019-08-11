package com.example.homework1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.second_activity.*

class SecondActivity : AppCompatActivity() {
    lateinit var counterTextView: TextView


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

        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        val myFragment = FirstFragment.newInstance(counterTextView.text.toString().toInt())
        transaction.add(R.id.fragment_container, myFragment, "Fragment")
        transaction.addToBackStack(null)
        transaction.commit()


        buttonFragment.setOnClickListener {

            // Get the support fragment manager instance
            val manager = supportFragmentManager

            // Begin the fragment transition using support fragment manager
            val transaction = manager.beginTransaction()

            val currentFragment = supportFragmentManager.findFragmentByTag("Fragment")

            // It's either SecondFragment or None.
            if (currentFragment is SecondFragment) {
                // Get the text fragment instance
                val myFragment = FirstFragment.newInstance(counterTextView.text.toString().toInt())

                // Replace the fragment on container
                transaction.replace(R.id.fragment_container, myFragment, "Fragment")
            } else {
                // Get the text fragment instance
                val myFragment = SecondFragment.newInstance()

                // Replace the fragment on container
                transaction.replace(R.id.fragment_container, myFragment, "Fragment")
            }

            transaction.addToBackStack(null)

            // Finishing the transition
            transaction.commit()
        }


    }

    fun startFirstActivity(@Suppress("UNUSED_PARAMETER") view: View?) {
        val intent = Intent(this, MainActivity::class.java)
        try {
            val cntTextViewValue = this.counterTextView.text.toString()
            intent.putExtra(COUNTER_ID, cntTextViewValue.toInt() + 1)
        } catch (e: NumberFormatException) {
            intent.putExtra(COUNTER_ID, 0)
        }

        startActivity(intent)
    }

}