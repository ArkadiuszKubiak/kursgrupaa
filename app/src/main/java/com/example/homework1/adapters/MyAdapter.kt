package com.example.homework1.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.homework1.BR
import com.example.homework1.pseudomodels.PseudoModelPersonList
import com.example.homework1.viewmodel.fragments.ItemListViewModel


class MyAdapter(
    @LayoutRes val layoutId: Int, val viewModel: ItemListViewModel,
    val personData: Array<PseudoModelPersonList>
) :
    RecyclerView.Adapter<MyAdapter.GenericViewHolder>() {


    class GenericViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: ItemListViewModel, position: Int?) {
            binding.setVariable(BR.viewmodel, viewModel)
            binding.setVariable(BR.position, position)
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GenericViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            layoutInflater,
            com.example.homework1.R.layout.item_list_row,
            parent,
            false
        )

        return GenericViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        holder.bind(viewModel, position)
    }

    private fun getLayoutIdForPosition(position: Int): Int {
        return layoutId
    }

    override fun getItemCount() = personData.size

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }
}
