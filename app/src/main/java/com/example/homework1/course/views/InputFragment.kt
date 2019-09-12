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
import com.example.homework1.course.database.OwnedPokemonRecord
import com.example.homework1.course.database.PokeDexRecord
import com.example.homework1.course.database.PokemonRecord
import com.example.homework1.course.viewmodels.PokeDexViewModel
import com.example.homework1.course.viewmodels.SharedViewModel
import kotlinx.android.synthetic.main.fragment_input.*


class InputFragment : Fragment() {

    companion object {
        fun newInstance(login: String): InputFragment {
            val fragment = InputFragment()
            val args = Bundle()
            args.putString("login", login)
            fragment.arguments = args
            return fragment
        }
    }

    var adapter: PokeAdapter? = null
    var allPokemons: List<PokemonRecord>? = null

    var trainersPokemon: List<OwnedPokemonRecord>? = null
    var trainerData: PokeDexRecord? = null

    lateinit var viewModel: PokeDexViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.run {
            ViewModelProviders.of(this)[PokeDexViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

        viewModel.getTrainerByName(arguments!!.getString("login")!!).observe(this, Observer { it ->
            run {
                trainerData = it
            }
        })

        viewModel.getOwnedPokemonsByTrainer(arguments!!.getString("login")!!).observe(this, Observer { it ->
            run {
                trainersPokemon = it
            }
        })

        viewModel.pokemonRepository.getAllPokemons().observe(this, Observer { it ->
            run {
                allPokemons = it
                adapter!!.addAll(it)
                model.setNumItems(adapter!!.getCount())
                model.setClickArray()
            }
        })

        listView1.onItemClickListener = AdapterView.OnItemClickListener { adapterView, _, position, id ->
            allPokemons?.get(position)?.name?.let { model.setPokName(it) }
            allPokemons?.get(position)?.pokemon_data!!.sprites.frontDefault.let { model.setImage(it) }
            model.setIndex(position)
            model.IncClick()
        }
    }
}
