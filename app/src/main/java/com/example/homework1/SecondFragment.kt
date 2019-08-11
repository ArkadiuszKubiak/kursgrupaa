package com.example.homework1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment


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
        val view = inflater.inflate(R.layout.second_fragment, container, false)
        val button = view.findViewById<Button>(R.id.buttonFragment)
        button.setOnClickListener {
            val myActivity = activity as SecondActivity
            val firstFragment = FirstFragment.newInstance(myActivity.counter)
            replaceFragment(firstFragment, R.id.fragment_container)
            myActivity.currentFragment = "FirstFragment"
        }

        return view
    }

}
