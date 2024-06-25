package com.akatsuki.safeguardpro.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akatsuki.safeguardpro.databinding.ListItemEpiBinding
import com.akatsuki.safeguardpro.service.model.Epi
import com.akatsuki.safeguardpro.service.model.Funcionario

class EpiAdapter(
    epis: List<Epi>?,
    private val clickListListener: (Epi) -> Unit
) :
    RecyclerView.Adapter<EpiAdapter.EpiViewHolder>() {

    //Criar uma lista vazia de EPI's
    private var epiList: List<Epi> = arrayListOf()

    class EpiViewHolder(private val binding: ListItemEpiBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //Carrega as informações do epi na lista
        fun bind(epi: Epi, clickListListener: (Epi) -> Unit) {
            binding.tvNomeEpi.text = epi.nomeEpi
            binding.tvCaEpi.text = epi.ca.toString()
            binding.tvValidadeFabricacaoEpi.text = epi.validadeFabricacao
            binding.tvValidadeTempoUsoEpi.text = epi.validadeTempoUso

            //Configura o click de algum item da lista
            binding.root.setOnClickListener {
                clickListListener(epi)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpiViewHolder {
        val listItemEpiBinding =
            ListItemEpiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EpiViewHolder(listItemEpiBinding)
    }

    override fun getItemCount(): Int {
        return epiList.count()
    }

    override fun onBindViewHolder(holder: EpiViewHolder, position: Int) {
        holder.bind(epiList[position], clickListListener)
    }

    fun updateEpi(list: List<Epi>) {
        epiList = list
        notifyDataSetChanged()
    }
}