package com.example.homework1

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.second_fragment.*




class SecondFragment : Fragment() {
    companion object {

        fun newInstance(): SecondFragment {
            return SecondFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view =  inflater.inflate(R.layout.second_fragment, container, false)
        val button = view.findViewById<Button>(R.id.buttonFragment)
        button.setOnClickListener {
            val myActivity = activity as SecondActivity
            val firstFragment = FirstFragment.newInstance(myActivity.counterTextView.text.toString().toInt())
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, firstFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        return view
    }

}
