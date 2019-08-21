package com.example.homework1.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework1.R
import com.example.homework1.adapters.MyAdapter
import com.example.homework1.databinding.ItemsFragmentBinding
import com.example.homework1.view.activities.MainActivity
import com.example.homework1.view.fragments.base.BaseFragment
import com.example.homework1.viewmodel.fragments.ItemsViewModel


class ItemListFragment : BaseFragment() {

    lateinit var binding: ItemsFragmentBinding


    private lateinit var viewModel: ItemsViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private val TAG = "ItemListFragment"

    companion object {
        fun newInstance() = ItemListFragment().apply {
            this.arguments = Bundle().apply {

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(ItemsViewModel::class.java)
        viewModel.init()

        viewManager = LinearLayoutManager(activity)
        viewAdapter = MyAdapter(R.layout.items_fragment, viewModel, viewModel.persons.value!!)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.items_fragment, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = activity

        recyclerView = binding.root.findViewById<RecyclerView>(R.id.item_list_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        viewModel.selectedPersonId.observe(this, Observer<Int> { selectedPersonId ->
            run {
                (activity as MainActivity).onSelectedPersonChanged(selectedPersonId)
            }
        })

        return binding.root
    }


}
