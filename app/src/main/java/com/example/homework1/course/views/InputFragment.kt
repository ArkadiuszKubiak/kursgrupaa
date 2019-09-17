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
import com.example.homework1.course.viewmodels.MyViewModelFactory
import com.example.homework1.course.viewmodels.PokeDexViewModel
import kotlinx.android.synthetic.main.fragment_input.*


class InputFragment : Fragment() {

    companion object {
        fun newInstance(login: String): InputFragment {
            val fragment = InputFragment()
            fragment.login = login
            return fragment
        }
    }

    private var login: String = ""

    var adapter: PokeAdapter? = null

    lateinit var viewModel: PokeDexViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.run {
            ViewModelProviders.of(this, MyViewModelFactory(this.application)).get(PokeDexViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        viewModel.loadTrainerData(login)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_input, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = activity?.let {
            PokeAdapter(
                it,
                mutableListOf()
            )
        }
        pokemonListView.adapter = adapter

        viewModel.trainerData.observe(this, Observer { it ->
            run {
                viewModel.loadTrainerData(it.login)
            }
        })

        viewModel.trainerPokemons.observe(this, Observer { trainersPokemon ->
            run {
                adapter!!.reloadPokemonData(trainersPokemon)
            }
        })

        pokemonListView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, _, position, id ->
            viewModel.selectedPokemon.value = viewModel.trainerPokemons.value?.get(position)
        }
    }
}
