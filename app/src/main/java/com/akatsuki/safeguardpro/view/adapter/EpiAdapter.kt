package com.akatsuki.safeguardpro.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akatsuki.safeguardpro.databinding.ListItemEpiBinding
import com.akatsuki.safeguardpro.service.model.Epi

class EpiAdapter(
    epi: List<Epi>?,
    private val clickListener: (Epi) -> Unit
) :
    RecyclerView.Adapter<EpiAdapter.EpiViewHolder>() {

    private var epiList: List<Epi> = arrayListOf()

    class EpiViewHolder(private val binding: ListItemEpiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(epi: Epi, clickListener: (Epi) -> Unit) {
            //setar informações
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpiViewHolder {
        val listItemEpiBinding =
            ListItemEpiBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return EpiViewHolder(listItemEpiBinding)
    }

    override fun onBindViewHolder(holder: EpiViewHolder, position: Int) {
        holder.bind(epiList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return epiList.count()
    }

    fun updateEpis(list: List<Epi>) {
        epiList = list
        notifyDataSetChanged()
    }
}