package com.example.homework1.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.homework1.R
import com.example.homework1.databinding.DetailsFragmentBinding
import com.example.homework1.viewmodel.fragments.DetailsViewModel


class ItemDetailsFragment : Fragment() {
    companion object {

        lateinit var binding: DetailsFragmentBinding
        lateinit var viewModel: DetailsViewModel

        fun newInstance(): ItemDetailsFragment {
            return ItemDetailsFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.details_fragment, container, false)
        viewModel = ViewModelProviders.of(activity!!).get(DetailsViewModel::class.java)
        viewModel.init()
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

}
