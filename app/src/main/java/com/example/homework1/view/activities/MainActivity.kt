package com.example.homework1.view.activities

import android.os.Bundle
import com.example.homework1.R
import com.example.homework1.addFragment
import com.example.homework1.view.activities.base.BaseActivity
import com.example.homework1.view.fragments.ItemDetailsFragment
import com.example.homework1.view.fragments.ItemListFragment


class MainActivity : BaseActivity() {

    private val TAG = "MainActivity"

    private lateinit var itemDetailsFragment: ItemDetailsFragment
    private lateinit var itemListFragment: ItemListFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        // SplashScreen
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)

        supportActionBar!!.hide()
        setContentView(R.layout.activity_main)

        // Initialize fragments.
        this.itemListFragment = ItemListFragment.newInstance()
        addFragment(this.itemListFragment, R.id.items_list_fragment_container)

        this.itemDetailsFragment = ItemDetailsFragment.newInstance()
        addFragment(this.itemDetailsFragment, R.id.item_details_fragment_container)
    }

    fun onSelectedPersonChanged(newSelectedPerson: Int) {
        this.itemDetailsFragment.binding.personId = newSelectedPerson
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            super.onSaveInstanceState(savedInstanceState)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            super.onRestoreInstanceState(savedInstanceState)
        }
    }
}
