package com.akatsuki.safeguardpro.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.akatsuki.safeguardpro.R
import com.akatsuki.safeguardpro.databinding.FragmentDetailFuncionarioBinding
import com.akatsuki.safeguardpro.databinding.FragmentRelatorioEpiBinding
import com.akatsuki.safeguardpro.databinding.FragmentRelatorioFuncionarioBinding
import com.akatsuki.safeguardpro.view.adapter.FuncionarioAdapter
import com.akatsuki.safeguardpro.viewmodel.RelatorioFuncionarioViewModel

class RelatorioEpiFragment : Fragment() {
    private val viewModel: RelatorioFuncionarioViewModel by viewModels()
    private lateinit var adapter: FuncionarioAdapter

    //Criar o binding
    private var _binding : FragmentRelatorioEpiBinding? = null
    private val binding : FragmentRelatorioEpiBinding get()= _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRelatorioEpiBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Quando clicar em algum item da lista
        adapter = FuncionarioAdapter(viewModel.funcionarioList.value) { funcionario ->

            findNavController().navigate(R.id.detailEPIFragment2, arguments)

        }

        //Configura a recycler
        val recycler = binding.rvListaEpi
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        viewModel.funcionarioList.observe(viewLifecycleOwner) {
            adapter.updateFuncionarios(it)
        }

        //Navegar para a tela de cadastro de pessoa
        binding.btnAdicionarEpi.setOnClickListener {
            findNavController().navigate(R.id.cadastroEpiFragment)
        }

        //Carregar as pessoas cadastradas
        viewModel.load()
    }
}
