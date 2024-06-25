package com.akatsuki.safeguardpro.view

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
    ): View {
        _binding = FragmentCadastroFuncionarioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            viewModel.getFuncionario(it.getInt("funcionarioId"))
        }

        binding.btnCadastrar.setOnClickListener {
            val nome = binding.edtNome.editableText.toString()
            val sobrenome = binding.edtSobrenome.editableText.toString()
            val cpf = binding.edtCpf.editableText.toString()
            val senha = binding.edtSenha.editableText.toString()
            val admin = binding.chkAdmin.isChecked

            if (nome != "" && sobrenome != "" && cpf != "" && senha != "") {
                val funcionario = Funcionario(
                    nome = nome,
                    sobrenome = sobrenome,
                    cpf = cpf,
                    admin = admin,
                    senha = senha
                )

                viewModel.funcionario.value?.let {
                    funcionario.id = it.id
                    viewModel.update(funcionario)
                } ?: run {
                    viewModel.insert(funcionario)
                }

                binding.edtNome.editableText.clear()
                binding.edtSobrenome.editableText.clear()
                binding.edtCpf.editableText.clear()
                binding.edtSenha.editableText.clear()
                binding.chkAdmin.isChecked = false
            } else {
                Toast.makeText(requireContext(), "Digite os dados", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnExcluir.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Exclusão de Funcionarios")
                .setMessage("Você realmente deseja excluir?")
                .setPositiveButton("Sim") { _, _ ->
                    viewModel.deleteFuncionario(viewModel.funcionario.value?.id ?: 0)
                }
                .setNegativeButton("Não") { _, _ -> }
                .show()
        }

        viewModel.funcionario.observe(viewLifecycleOwner) { funcionario ->
            binding.edtNome.setText(funcionario.nome)
            binding.edtSobrenome.setText(funcionario.sobrenome)
            binding.edtCpf.setText(funcionario.cpf)
            binding.edtSenha.setText(funcionario.senha)
            binding.chkAdmin.isChecked = funcionario.admin

            binding.btnExcluir.visibility = View.VISIBLE
        }

        viewModel.updatedFuncionario.observe(viewLifecycleOwner) {
            findNavController().navigateUp()
        }

        viewModel.deletedFuncionario.observe(viewLifecycleOwner) {
            findNavController().navigateUp()
        }

        viewModel.erro.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Erro $it", Toast.LENGTH_LONG).show()
            Log.e("Erro ao caadastrar funcionário !!", it)
        }
    }
}