package com.example.homework1

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment


class FirstFragment : Fragment() {
    private var counter = 0

    companion object {
        fun newInstance(Counter: Int) = FirstFragment().apply {
            this.arguments = Bundle().apply {
                putInt("Counter", Counter)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.first_fragment, container, false)

        val tv = view.findViewById(R.id.cntTextView) as TextView
        tv.text = this.counter.toString()

        val button = view.findViewById<Button>(R.id.buttonFragment)
        button.setOnClickListener {
            val secondFragment = SecondFragment.newInstance()
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, secondFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.arguments?.getInt("Counter")?.let {
            this.counter = it
        }
    }
}
