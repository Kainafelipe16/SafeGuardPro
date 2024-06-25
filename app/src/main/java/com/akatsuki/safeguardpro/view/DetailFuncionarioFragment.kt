package com.akatsuki.safeguardpro.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.akatsuki.safeguardpro.databinding.FragmentDetailFuncionarioBinding
import com.akatsuki.safeguardpro.viewmodel.FuncionarioViewModel

class DetailFuncionarioFragment : Fragment() {
    private val viewModel: FuncionarioViewModel by viewModels()

    //Criar o binding
    private var _binding: FragmentDetailFuncionarioBinding? = null
    private val binding: FragmentDetailFuncionarioBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // configurar binding
        _binding = FragmentDetailFuncionarioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            viewModel.getFuncionario(it.getInt("funcionarioId"))
        }

        viewModel.funcionario.observe(viewLifecycleOwner) { funcionario ->
            binding.tvNomeFuncionario.text = funcionario.nome
            binding.tvSobrenomeFuncionario.text = funcionario.sobrenome
            binding.tvCpfFuncionario.text = funcionario.cpf
        }

        viewModel.erro.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Erro $it", Toast.LENGTH_LONG).show()
            Log.e("Erro ao caadastrar funcionário !!", it)
        }
    }
}