package com.example.homework1.view.activities

import android.os.Bundle
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
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

    private lateinit var viewModel: MainViewModel

    private lateinit var itemDetailsFragment: ItemDetailsFragment
    private lateinit var itemListFragment: ItemListFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        // SplashScreen
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)

        // Bindings
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        this.binding.viewmodel = this.viewModel
        this.binding.lifecycleOwner = this

        // Initialize fragments.
        this.itemListFragment = ItemListFragment.newInstance()
        addFragment(this.itemListFragment, R.id.items_list_fragment_container)

        this.itemDetailsFragment = ItemDetailsFragment.newInstance()
        addFragment(this.itemDetailsFragment, R.id.item_details_fragment_container)

    }
}
