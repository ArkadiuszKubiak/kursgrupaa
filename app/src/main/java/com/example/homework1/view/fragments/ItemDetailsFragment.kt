package com.example.homework1.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.homework1.R
import com.example.homework1.databinding.DetailsItemFragmentBinding
import com.example.homework1.databinding.ItemListFragmentBinding


class ItemDetailsFragment : Fragment() {
    companion object {

        lateinit var binding: DetailsItemFragmentBinding

        fun newInstance(): ItemDetailsFragment {
            return ItemDetailsFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.item_list_fragment, container, false);
        return binding.root
    }

}
