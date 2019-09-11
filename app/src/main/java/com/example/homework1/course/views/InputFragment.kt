package com.example.homework1.course.views


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.homework1.R
import com.example.homework1.course.adapters.PokeAdapter
import com.example.homework1.course.database.AppDatabase
import com.example.homework1.course.database.PokemonRecord
import com.example.homework1.course.database.PokemonRepository
import com.example.homework1.course.rest_api.ApiClient
import com.example.homework1.course.viewmodels.SharedViewModel
import kotlinx.android.synthetic.main.fragment_input.*


class InputFragment : Fragment() {

    var adapter: PokeAdapter? = null
    var customers: List<PokemonRecord>? = null

    lateinit var pokeRepo: PokemonRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pokeRepo = PokemonRepository.getInstance(AppDatabase.getInstance(context = this.activity!!.baseContext), ApiClient.getClient)
        return inflater.inflate(R.layout.fragment_input, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val model = ViewModelProviders.of(activity!!).get(SharedViewModel::class.java)


        adapter = activity?.let {
            PokeAdapter(
                it,
                mutableListOf()
            )
        }
        listView1.adapter = adapter

        pokeRepo.getAllPokemons().observe(this, Observer { it ->
            run {
                customers = it
                adapter!!.addAll(it)
                model.setNumItems(adapter!!.getCount())
                model.setClickArray()
            }
        })

        listView1.onItemClickListener = AdapterView.OnItemClickListener { adapterView, _, position, id ->
            customers?.get(position)?.name?.let { model.setPokName(it) }
            customers?.get(position)?.pokemon_data!!.sprites.frontDefault.let { model.setImage(it) }
            model.setIndex(position)
            model.IncClick()
        }
    }
}
