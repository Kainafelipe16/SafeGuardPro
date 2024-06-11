package com.akatsuki.safeguardpro.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.akatsuki.safeguardpro.R
import com.akatsuki.safeguardpro.databinding.FragmentCadastroEpiBinding
import com.akatsuki.safeguardpro.databinding.FragmentCadastroFuncionarioBinding
import com.akatsuki.safeguardpro.service.model.Epi
import com.akatsuki.safeguardpro.viewmodel.EpiViewModel
import okhttp3.internal.notifyAll

class CadastroEpiFragment : Fragment() {
    private val viewModel: EpiViewModel by viewModels()

    private var _binding: FragmentCadastroEpiBinding? = null
    private val binding: FragmentCadastroEpiBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCadastroEpiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            viewModel.getEpi(it.getInt("epiId"))
        }
        binding.btnCadastrar.setOnClickListener {
            var nomeEpi = binding.edtNomeEpi.editableText.toString()
            var descricao = binding.edtDescricao.editableText.toString()
            var Ca = binding.edtCa.editableText.toString()
            var validadeFabricacao = binding.edtValidadeFabricacao.editableText.toString()
            var validadeTempoUso = binding.edtValidadeTempoUso.editableText.toString()

            val epi = Epi(
                nomeEpi = nomeEpi,
                descricao = descricao,
                Ca = Ca,
                validadeFabricacao = validadeFabricacao,
                validadeTempoUso = validadeTempoUso
            )
            viewModel.epi.value?.let {
                epi.id = it.id
                viewModel.update(epi)
            } ?: run {
                viewModel.insert(epi)
            }

            viewModel.deletedEpi.observe(viewLifecycleOwner) {
                findNavController().navigateUp()
            }

            viewModel.epi.observe(viewLifecycleOwner) {
                binding.edtNomeEpi.visibility = View.VISIBLE
                binding.edtDescricao.visibility = View.VISIBLE
                binding.edtCa.visibility = View.VISIBLE
                binding.edtValidadeFabricacao.visibility = View.VISIBLE
                binding.edtValidadeTempoUso.visibility = View.VISIBLE

                binding.edtNomeEpi.setText(it.nomeEpi)
                binding.edtDescricao.setText(it.descricao)
                binding.edtCa.setText(it.Ca)
                binding.edtValidadeFabricacao.setText(it.validadeFabricacao)
                binding.edtValidadeTempoUso.setText(it.validadeTempoUso)
            }
        }
    }
}
