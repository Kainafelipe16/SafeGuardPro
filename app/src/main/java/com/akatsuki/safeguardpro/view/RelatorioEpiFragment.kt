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
import com.akatsuki.safeguardpro.databinding.FragmentRelatorioEpiBinding
import com.akatsuki.safeguardpro.view.adapter.EpiAdapter
import com.akatsuki.safeguardpro.view.adapter.FuncionarioAdapter
import com.akatsuki.safeguardpro.viewmodel.EpiViewModel
import com.akatsuki.safeguardpro.viewmodel.FuncionarioViewModel

class RelatorioEpiFragment : Fragment() {
    private val viewModel: EpiViewModel by viewModels()
    private lateinit var adapter: EpiAdapter

    //Criar o binding
    private var _binding : FragmentRelatorioEpiBinding? = null
    private val binding : FragmentRelatorioEpiBinding get()= _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRelatorioEpiBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Quando clicar em algum item da lista
        adapter = EpiAdapter(viewModel.epiList.value) { epi ->
            val epiBundle = Bundle()
            epiBundle.putInt("epiId", epi.id)
            arguments = epiBundle
            findNavController().navigate(R.id.detailEPIFragment2, arguments)
        }

        //Configura a recycler
        val recycler = binding.rvListaEpi
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        viewModel.epiList.observe(viewLifecycleOwner) {
            adapter.updateEpi(it)
        }

        viewModel.erro.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Erro EPI: $it", Toast.LENGTH_LONG).show()
            Log.e("erro epi", it)
        }

        //Navegar para a tela de cadastro de pessoa
        binding.btnAdicionarEpi.setOnClickListener {
            findNavController().navigate(R.id.cadastroEpiFragment)
        }

        //Carregar as pessoas cadastradas
        viewModel.load()
    }
}
