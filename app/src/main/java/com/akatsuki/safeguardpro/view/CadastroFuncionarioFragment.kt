package com.akatsuki.safeguardpro.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.akatsuki.safeguardpro.databinding.FragmentCadastroFuncionarioBinding
import com.akatsuki.safeguardpro.service.model.Funcionario
import com.akatsuki.safeguardpro.viewmodel.FuncionarioViewModel

class CadastroFuncionarioFragment : Fragment() {

    private val viewModel: FuncionarioViewModel by viewModels()

    private var _binding: FragmentCadastroFuncionarioBinding? = null

    private val binding: FragmentCadastroFuncionarioBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCadastroFuncionarioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            viewModel.getFuncionario(it.getInt("funcionarioId"))
        }

        binding.btnCadastrar.setOnClickListener {
            var nome = binding.edtNome.editableText.toString()
            var sobrenome = binding.edtEmail.editableText.toString()
            var cpf = binding.edtCpf.editableText.toString()


            val funcionario = Funcionario(
                nome = nome,
                sobrenome = sobrenome,
                cpf = cpf
            )

            viewModel.funcionario.value?.let {
                funcionario.id = it.id
                viewModel.update(funcionario)
            } ?: run {
                viewModel.insert(funcionario)
            }

            viewModel.updatedFuncionario.observe(viewLifecycleOwner) {
                findNavController().navigateUp()
            }

            viewModel.deletedFuncionario.observe(viewLifecycleOwner) {
                findNavController().navigateUp()
            }
        }
    }
}
