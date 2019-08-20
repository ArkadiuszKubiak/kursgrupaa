package com.example.homework1.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.homework1.R
import com.example.homework1.databinding.DetailsItemFragmentBinding
import com.example.homework1.databinding.ItemListFragmentBinding
import com.example.homework1.viewmodel.activities.MainViewModel
import com.example.homework1.viewmodel.fragments.ItemsDetailViewModel


class ItemDetailsFragment : Fragment() {
    companion object {

        lateinit var binding: DetailsItemFragmentBinding
        lateinit var viewModel: ItemsDetailViewModel

        fun newInstance(): ItemDetailsFragment {
            return ItemDetailsFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.details_item_fragment, container, false)
        viewModel = ViewModelProviders.of(activity!!).get(ItemsDetailViewModel::class.java)
        viewModel.init()
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

}
