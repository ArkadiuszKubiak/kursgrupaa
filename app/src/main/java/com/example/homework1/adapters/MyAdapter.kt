package com.example.homework1.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.homework1.BR
import com.example.homework1.R
import com.example.homework1.pseudomodels.PseudoModelPerson
import com.example.homework1.pseudomodels.PseudoModelPersonList
import com.example.homework1.viewmodel.fragments.ItemListViewModel

class MyAdapter(val viewModel: ItemListViewModel, val personData: Array<PseudoModelPersonList>) :
    RecyclerView.Adapter<MyAdapter.GenericViewHolder>() {


    class GenericViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: ItemListViewModel, position: Int?) {
            viewModel.getPersonAtPosition(position!!)
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
        val binding = layoutInflater.inflate(R.layout.item_list_row, parent, false) as ViewDataBinding

        return GenericViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        holder.bind(viewModel, position)
    }

    override fun getItemCount() = personData.size
}
