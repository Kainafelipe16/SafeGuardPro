package com.akatsuki.safeguardpro.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.akatsuki.safeguardpro.R
import com.akatsuki.safeguardpro.databinding.FragmentRelatorioFuncionarioBinding
import com.akatsuki.safeguardpro.service.model.Login
import com.akatsuki.safeguardpro.view.adapter.FuncionarioAdapter
import com.akatsuki.safeguardpro.viewmodel.FuncionarioViewModel

class RelatorioFuncionarioFragment : Fragment() {
    private val viewModel: FuncionarioViewModel by viewModels()
    private lateinit var adapter: FuncionarioAdapter

    //Criar o Binding
    private var _binding: FragmentRelatorioFuncionarioBinding? = null
    private val binding: FragmentRelatorioFuncionarioBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRelatorioFuncionarioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Quando clicar em algum item da lista
        adapter = FuncionarioAdapter(viewModel.funcionarioList.value) { funcionario ->
            val funcionarioBundle = Bundle()
            funcionarioBundle.putInt("funcionarioId", funcionario.id)
            arguments = funcionarioBundle
            if (Login.userAdmin) {
                findNavController().navigate(R.id.cadastroFuncionarioFragment, arguments)
            } else {
                findNavController().navigate(R.id.detailFuncionarioFragment, arguments)
            }
        }

        //Configura a recycler
        val recycler = binding.rvListaFuncionario
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        viewModel.funcionarioList.observe(viewLifecycleOwner) {
            adapter.updateFuncionarios(it)
        }

        //Navegar para a tela de cadastro de pessoa
        binding.imgBtnAdd.setOnClickListener {
            findNavController().navigate(R.id.cadastroFuncionarioFragment)
        }

        //Carregar as pessoas cadastradas
        viewModel.load()

        viewModel.erro.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Erro Funcionario: $it", Toast.LENGTH_LONG).show()
            Log.e("erro funcionario", it)
        }
    }
}