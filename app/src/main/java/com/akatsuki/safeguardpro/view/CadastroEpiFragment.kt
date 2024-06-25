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
import com.akatsuki.safeguardpro.databinding.FragmentCadastroEpiBinding
import com.akatsuki.safeguardpro.service.model.Epi
import com.akatsuki.safeguardpro.service.model.Login
import com.akatsuki.safeguardpro.viewmodel.EpiViewModel

class CadastroEpiFragment : Fragment() {
    private val viewModel: EpiViewModel by viewModels()

    private var _binding: FragmentCadastroEpiBinding? = null
    private val binding: FragmentCadastroEpiBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCadastroEpiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            viewModel.getEpi(it.getInt("epiId"))
        }

        binding.btnCadastrar.setOnClickListener {
            val nomeEpi = binding.edtNomeEpi.editableText.toString()
            val descricao = binding.edtDescricao.editableText.toString()
            val ca = binding.edtCa.editableText.toString().toInt()
            val validadeFabricacao = binding.edtValidadeFabricacao.editableText.toString()
            val validadeTempoUso = binding.edtValidadeTempoUso.editableText.toString()

            if (nomeEpi != "" && validadeFabricacao != "" && descricao != "" && ca != 0 && validadeTempoUso != "") {
                val epi = Epi(
                    nomeEpi = nomeEpi,
                    descricao = descricao,
                    ca = ca,
                    validadeFabricacao = validadeFabricacao,
                    validadeTempoUso = validadeTempoUso
                )

                viewModel.epi.value?.let {
                    epi.id = it.id
                    viewModel.update(epi)
                } ?: run {
                    viewModel.insert(epi)
                }

                binding.edtNomeEpi.editableText.clear()
                binding.edtDescricao.editableText.clear()
                binding.edtCa.editableText.clear()
                binding.edtValidadeFabricacao.editableText.clear()
                binding.edtValidadeTempoUso.editableText.clear()
            } else {
                Toast.makeText(requireContext(), "Digite os dados", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnExcluir.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Exclusão de EPIs")
                .setMessage("Você realmente deseja excluir?")
                .setPositiveButton("Sim") { _, _ ->
                    viewModel.delete(viewModel.epi.value?.id ?: 0)
                    findNavController().navigateUp()
                }
                .setNegativeButton("Não") { _, _ -> }
                .show()
        }

        viewModel.createdEpi.observe(viewLifecycleOwner) {
            findNavController().navigateUp()
        }

        viewModel.updatedEpi.observe(viewLifecycleOwner) {
            findNavController().navigateUp()
        }

        viewModel.deletedEpi.observe(viewLifecycleOwner) {
            findNavController().navigateUp()
        }

        viewModel.epi.observe(viewLifecycleOwner) {
            binding.edtNomeEpi.setText(it.nomeEpi)
            binding.edtDescricao.setText(it.descricao)
            binding.edtCa.setText(it.ca.toString())
            binding.edtValidadeFabricacao.setText(it.validadeFabricacao)
            binding.edtValidadeTempoUso.setText(it.validadeTempoUso)

            if (Login.userAdmin) {
                binding.btnExcluir.visibility = View.VISIBLE
            } else {
                binding.tvCadastroEpi.text = "DETALHES EPI"

                binding.edtNomeEpi.isClickable = false
                binding.edtDescricao.isClickable = false
                binding.edtCa.isClickable = false
                binding.edtValidadeFabricacao.isClickable = false
                binding.edtValidadeTempoUso.isClickable = false

                binding.btnCadastrar.visibility = View.GONE
            }
        }

        viewModel.erro.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Erro $it", Toast.LENGTH_LONG).show()
            Log.e("erro Emprestimo", it)
        }
    }
}