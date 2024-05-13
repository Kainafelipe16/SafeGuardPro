package com.akatsuki.safeguardpro.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.akatsuki.safeguardpro.databinding.ListItemFuncionarioBinding
import com.akatsuki.safeguardpro.service.model.Funcionario
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import java.text.ParsePosition

class FuncionarioAdapter(
    funcionario: List<Funcionario>?,
    private val clickListener: (Funcionario) -> Unit
) :
    RecyclerView.Adapter<FuncionarioAdapter.FuncionarioViewHolder>() {

    private var funcionarioList: List<Funcionario> = arrayListOf()

    class FuncionarioViewHolder(private val binding: ListItemFuncionarioBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(funcionario: Funcionario, clickListener: (Funcionario) -> Unit) {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FuncionarioViewHolder {
        val listItemFuncionarioBinding =
            ListItemFuncionarioBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return FuncionarioViewHolder(listItemFuncionarioBinding)
    }

    override fun onBindViewHolder(holder: FuncionarioViewHolder, position: Int) {
        holder.bind(funcionarioList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return funcionarioList.count()
    }

    fun updateFuncionarios(list: List<Funcionario>) {
        funcionarioList = list
        notifyDataSetChanged()
    }
}
