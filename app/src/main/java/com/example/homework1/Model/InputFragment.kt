package com.example.homework1


import android.app.Activity
import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.homework1.Model.PokemonClass
import kotlinx.android.synthetic.main.fragment_input.*
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call


class InputFragment : Fragment() {

    private var sharedViewModel: SharedViewModel? = null
    private var myObjects = ArrayList<Pokemon>()
    var adapter: MyAdapter? = null
    lateinit var progerssProgressDialog: ProgressDialog
    var dataList = ArrayList<Pokemon>()
    var numclick = ArrayList<Int>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        return inflater.inflate(R.layout.fragment_input, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        sharedViewModel = ViewModelProviders.of(activity!!).get(SharedViewModel::class.java)

        progerssProgressDialog= ProgressDialog(context)
        progerssProgressDialog.setTitle("Loading")
        progerssProgressDialog.setCancelable(false)
        progerssProgressDialog.show()

        val call: Call<PokemonClass> = ApiClient.getClient.getPokemons()
        call.enqueue(object : Callback<PokemonClass> {

            override fun onResponse(call: Call<PokemonClass>?, response: Response<PokemonClass>?) {
                progerssProgressDialog.dismiss()
                dataList.addAll(response!!.body()!!.pokemon!!)

                sharedViewModel!!.setNumItems(dataList.size)

                adapter = activity?.let { MyAdapter(it, dataList) }
                sharedViewModel!!.setClickArray()

                listView1.adapter = adapter

                listView1.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
                    sharedViewModel!!.setPokName(dataList[position].name)
                    sharedViewModel!!.setImage(dataList[position].img)
                    sharedViewModel!!.setIndex(position)
                    sharedViewModel!!.IncClick()
                }
            }

            override fun onFailure(call: Call<PokemonClass>?, t: Throwable?) {
                progerssProgressDialog.dismiss()
            }
        })
    }
}