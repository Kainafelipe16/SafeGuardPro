package com.akatsuki.safeguardpro.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.akatsuki.safeguardpro.R
import com.akatsuki.safeguardpro.databinding.FragmentTelaInicialSupervisorBinding
import com.akatsuki.safeguardpro.service.model.Login

class TelaInicialSupervisorFragment : Fragment() {
    private var _binding: FragmentTelaInicialSupervisorBinding? = null
    private val binding: FragmentTelaInicialSupervisorBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTelaInicialSupervisorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (Login.userAdmin) {
            binding.cvRelatorioFuncionario.setOnClickListener {
                findNavController().navigate(R.id.relatorioFuncionarioFragment)
            }

            binding.cvRelatorioEpi.setOnClickListener {
                findNavController().navigate(R.id.relatorioEpiFragment)
            }

            binding.cvEmprestimo.setOnClickListener {
                findNavController().navigate(R.id.relatorioEmprestimoFragment)
            }
        } else {
            binding.cvRelatorioFuncionario.setOnClickListener {
                val funcionarioBundle = Bundle()
                funcionarioBundle.putInt("funcionarioId", Login.userId)
                arguments = funcionarioBundle
                findNavController().navigate(R.id.detailFuncionarioFragment, arguments)
            }

            binding.cvRelatorioEpi.setOnClickListener {
                findNavController().navigate(R.id.relatorioEpiFragment)
            }

            binding.cvEmprestimo.visibility = View.GONE
        }
    }
}