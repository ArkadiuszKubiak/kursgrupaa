package com.example.homework1.view.activities

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.homework1.R
import com.example.homework1.addFragment
import com.example.homework1.databinding.ActivityMainBinding
import com.example.homework1.view.activities.base.BaseActivity
import com.example.homework1.view.fragments.ItemDetailsFragment
import com.example.homework1.view.fragments.ItemListFragment
import com.example.homework1.viewmodel.activities.MainViewModel


class MainActivity : BaseActivity() {

    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel: MainViewModel

    private lateinit var itemDetailsFragment: ItemDetailsFragment
    private lateinit var itemListFragment: ItemListFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        // SplashScreen
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)

        supportActionBar!!.hide()


        // Bindings
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.init()

        this.binding.viewmodel = this.viewModel
        this.binding.lifecycleOwner = this

        // Initialize fragments.
        this.itemListFragment = ItemListFragment.newInstance()
        addFragment(this.itemListFragment, R.id.items_list_fragment_container)

        this.itemDetailsFragment = ItemDetailsFragment.newInstance()
        addFragment(this.itemDetailsFragment, R.id.item_details_fragment_container)

        viewModel.chosenPseudoModelPersonId.observe(this, Observer<Int> { selectedPersonId ->
            run {
                Log.d(TAG, "onchosenPseudoModelPersonIdChanged: ")
                this.itemDetailsFragment.binding.personId = selectedPersonId
            }
        })

    }
}
