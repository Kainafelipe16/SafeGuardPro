package com.akatsuki.safeguardpro.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akatsuki.safeguardpro.R
import com.akatsuki.safeguardpro.databinding.FragmentDetailEpiBinding

class DetailEpiFragment : Fragment(){
    private var _binding: FragmentDetailEpiBinding? = null
    private val binding: FragmentDetailEpiBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailEpiBinding.inflate(inflater, container, false)
        return binding.root
    }
}