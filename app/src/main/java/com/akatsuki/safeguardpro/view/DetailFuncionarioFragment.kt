package com.akatsuki.safeguardpro.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.akatsuki.safeguardpro.R
import com.akatsuki.safeguardpro.databinding.FragmentDetailEPIBinding
import com.akatsuki.safeguardpro.databinding.FragmentDetailFuncionarioBinding


class DetailFuncionarioFragment : Fragment() {


    //Criar o binding
    private var _binding : FragmentDetailFuncionarioBinding? = null
    private val binding : FragmentDetailFuncionarioBinding get()= _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Configurar binding
        _binding = FragmentDetailFuncionarioBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}