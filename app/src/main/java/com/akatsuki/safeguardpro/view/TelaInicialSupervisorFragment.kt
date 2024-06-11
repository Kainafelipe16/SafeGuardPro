package com.akatsuki.safeguardpro.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.akatsuki.safeguardpro.R
import com.akatsuki.safeguardpro.databinding.FragmentTelaInicialSupervisorBinding

class TelaInicialSupervisorFragment : Fragment() {
    private var _binding: FragmentTelaInicialSupervisorBinding? = null
    private val binding: FragmentTelaInicialSupervisorBinding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTelaInicialSupervisorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cvRelatorioFuncionario.setOnClickListener{
            findNavController().navigate(R.id.relatorioFuncionarioFragment)
        }
    }
}