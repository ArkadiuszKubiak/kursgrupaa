package com.example.homework1.course.views


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.homework1.R
import com.example.homework1.course.adapters.PokeAdapter
import com.example.homework1.course.database.AppDatabase
import com.example.homework1.course.database.PoksRecord
import com.example.homework1.course.viewmodels.SharedViewModel
import kotlinx.android.synthetic.main.fragment_input.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class InputFragment : Fragment() {

    var adapter: PokeAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_input, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val model = ViewModelProviders.of(activity!!).get(SharedViewModel::class.java)
        var customers : List<PoksRecord>? = null

        adapter = activity?.let {
            PokeAdapter(
                it,
                mutableListOf()
            )
        }
        listView1.adapter = adapter

        doAsync {

            val database = activity?.let { AppDatabase.getInstance(it) }
            customers = database?.pokemonDao()?.getAll()!!

            uiThread {
                adapter!!.addAll(customers)
                model.setNumItems(adapter!!.getCount())
                model.setClickArray()
            }
        }

        listView1.onItemClickListener = AdapterView.OnItemClickListener { adapterView, _, position, id ->
            customers?.get(position)?.name?.let { model.setPokName(it) }
            customers?.get(position)?.img?.let { model.setImage(it) }
            model.setIndex(position)
            model.IncClick()
        }
    }
}