package com.akatsuki.safeguardpro.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akatsuki.safeguardpro.databinding.ListItemEmprestimoBinding
import com.akatsuki.safeguardpro.service.model.Emprestimo

class EmprestimoAdapter(
    emprestimos: List<Emprestimo>?,
    private val clickListener: (Emprestimo) -> Unit
) : RecyclerView.Adapter<EmprestimoAdapter.EmprestimoViewHolder>() {

    private var emprestimoList: List<Emprestimo> = arrayListOf()

    class EmprestimoViewHolder(private val binding: ListItemEmprestimoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(emprestimo: Emprestimo, clickListener: (Emprestimo) -> Unit) {
            binding.tvIdEmprestimo.text = emprestimo.emprestimo_id.toString()
            binding.tvDataEmprestimo.text = emprestimo.dataEmprestimo
            binding.tvIdFuncionario.text = emprestimo.funcionario_fk.toString()
            binding.tvIdEpi.text = emprestimo.epi_fk.toString()

            binding.root.setOnClickListener {
                clickListener(emprestimo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmprestimoViewHolder {
        val ListItemEmprestimoBinding =
            ListItemEmprestimoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmprestimoViewHolder(ListItemEmprestimoBinding)
    }

    override fun getItemCount(): Int {
        return emprestimoList.count()
    }

    override fun onBindViewHolder(holder: EmprestimoViewHolder, position: Int) {
        holder.bind(emprestimoList[position], clickListener)
    }

    fun updateEmprestimo(list: List<Emprestimo>) {
        emprestimoList = list
        notifyDataSetChanged()
    }
}