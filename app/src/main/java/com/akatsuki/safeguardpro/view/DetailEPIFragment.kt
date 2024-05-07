package com.akatsuki.safeguardpro.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.akatsuki.safeguardpro.R
import com.akatsuki.safeguardpro.databinding.ActivityMainBinding
import com.akatsuki.safeguardpro.databinding.FragmentDetailEPIBinding

class DetailEPIFragment : Fragment() {


    //Criar o binding
    private var _binding : FragmentDetailEPIBinding? = null
    private val binding : FragmentDetailEPIBinding get()= _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Configurar o binding
        _binding = FragmentDetailEPIBinding.inflate(inflater, container, false)
        return binding.root
    }
}