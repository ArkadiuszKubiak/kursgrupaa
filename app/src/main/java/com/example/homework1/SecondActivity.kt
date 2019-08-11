package com.example.homework1

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity(), FirstFragment.OnFragmentInteractionListener, SecondFragment.OnFragmentInteractionListener  {

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var counter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        counter  = intent.getIntExtra(COUNTER_ID, 0)

        if (savedInstanceState == null) {
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.flContent, FirstFragment.newInstance(counter))
            transaction.commit()
        }
        if (savedInstanceState == null) {
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.flContent2, SecondFragment.newInstance())
            transaction.commit()
        }
    }

    override fun onBackPressed() {
        counter++
        val returnIntent = this.intent
        returnIntent.putExtra(COUNTER_ID, counter)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }
}