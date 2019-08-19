package com.example.homework1.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework1.R
import com.example.homework1.adapters.MyAdapter
import com.example.homework1.viewmodel.activities.MainViewModel


class ItemListFragment : Fragment(), View.OnClickListener {

    private lateinit var viewModelBasic: MainViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    companion object {
        fun newInstance() = ItemListFragment().apply {
            this.arguments = Bundle().apply {

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewManager = LinearLayoutManager(this)
        viewAdapter = MyAdapter(myDataset)

        //val model = ViewModelProviders.of(this).get(NewMovieViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.details_item_fragment, container, false)

        viewManager = LinearLayoutManager(activity)

        viewAdapter = MyAdapter(myDataset)

        recyclerView = view.findViewById<RecyclerView>(R.id.my_recycler_view).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }
        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModelBasic = MainViewModel()

        //viewModelBasic.uiModel.observe(this, Observer<UiModel> { uiModel ->
        }




    override fun onClick(view: View?) {

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        //this.arguments?.getInt("Counter")?.let {
          //  this.counter = it
        //}
    }
}
