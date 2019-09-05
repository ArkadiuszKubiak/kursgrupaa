package com.example.homework1.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.homework1.BR
import com.example.homework1.models.PersonListModel
import com.example.homework1.viewmodel.fragments.ItemsViewModel


class MyAdapter(
    @LayoutRes val layoutId: Int, val viewModel: ItemsViewModel,
    val personDatumModels: Array<PersonListModel>
) :
    RecyclerView.Adapter<MyAdapter.GenericViewHolder>() {


    class GenericViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: ItemsViewModel, position: Int?) {
            binding.setVariable(BR.viewmodel, viewModel)
            binding.setVariable(BR.position, position)
            //binding.setVariable(BR.person_id, viewModel.selectedPersonId.value)
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
            com.example.homework1.R.layout.row_item,
            parent,
            false
        )


        return GenericViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        holder.bind(viewModel, position)
    }

    override fun getItemCount() = personDatumModels.size

    override fun getItemViewType(position: Int): Int {
        return layoutId
    }
}
