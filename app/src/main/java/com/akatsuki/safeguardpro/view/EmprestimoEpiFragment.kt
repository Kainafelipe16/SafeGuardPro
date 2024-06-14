package com.akatsuki.safeguardpro.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.akatsuki.safeguardpro.databinding.FragmentEmprestimoEpiBinding
import com.akatsuki.safeguardpro.service.model.Emprestimo
import com.akatsuki.safeguardpro.service.model.Epi
import com.akatsuki.safeguardpro.service.model.Funcionario
import com.akatsuki.safeguardpro.viewmodel.EmprestimoViewModel
import com.akatsuki.safeguardpro.viewmodel.EpiViewModel
import com.akatsuki.safeguardpro.viewmodel.FuncionarioViewModel

class EmprestimoEpiFragment : Fragment() {
    private val viewModel: EmprestimoViewModel by viewModels()
    private val viewModelEpi: EpiViewModel by viewModels()
    private val viewModelFuncionario: FuncionarioViewModel by viewModels()

    private lateinit var epiCa: Epi
    private lateinit var funcionarioCpf: Funcionario

    private var _binding: FragmentEmprestimoEpiBinding? = null
    private val binding: FragmentEmprestimoEpiBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmprestimoEpiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            viewModel.getEmprestimo(it.getInt("emprestimo_id"))
        }

        binding.btnAtribuirEpi.setOnClickListener {
            val cpf = binding.edtCpfFuncionario.editableText.toString()
            val ca = binding.edtCaEpi.editableText.toString().toInt()

            if (cpf != "" && ca != 0) {
                viewModelEpi.getEpiByCa(ca)
                viewModelFuncionario.getFuncionarioByCpf(cpf)

                val emprestimo = Emprestimo(
                    dataEmprestimo = "",
                    funcionario_fk = funcionarioCpf.id,
                    epi_fk = epiCa.id
                )

                viewModel.emprestimo.value?.let {
                    emprestimo.emprestimo_id = it.emprestimo_id
                    viewModel.update(emprestimo)
                } ?: run {
                    viewModel.insert(emprestimo)
                }

                binding.edtCpfFuncionario.editableText.clear()
                binding.edtCaEpi.editableText.clear()
            }
        }

        binding.btnDeletarEmprestimo.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Exclusão de epi")
                .setMessage("Você realmente deseja excluir esse epi?")
                .setPositiveButton("Sim") { _, _ ->
                    viewModel.delete(viewModel.emprestimo.value?.emprestimo_id ?: 0)
                }
                .setNegativeButton("Não") { _, _ -> }
                .show()

            binding.btnDeletarEmprestimo.visibility = View.GONE
        }

        viewModel.createEmprestimo.observe(viewLifecycleOwner) {
            if (it.emprestimo_id == 0) {
                Toast.makeText(
                    requireContext(),
                    "Não é possivel criar o empréstimo!!",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Empréstimo ${it.emprestimo_id} atribuído com sucesso !!",
                    Toast.LENGTH_LONG
                ).show()

                findNavController().navigateUp()
            }
        }

        viewModel.emprestimo.observe(viewLifecycleOwner) {
            viewModelEpi.getEpi(it.epi_fk)
            viewModelFuncionario.getFuncionario(it.funcionario_fk)
            binding.btnDeletarEmprestimo.visibility = View.VISIBLE
        }

        viewModelEpi.epi.observe(viewLifecycleOwner) {
            epiCa = it
            binding.edtCaEpi.setText(it.ca)
        }

        viewModelFuncionario.funcionario.observe(viewLifecycleOwner) {
            funcionarioCpf = it
            binding.edtCpfFuncionario.setText(it.cpf)
        }

        viewModel.deleteEmprestimo.observe(viewLifecycleOwner) {
            findNavController().navigateUp()
        }

        viewModel.updateEmprestimo.observe(viewLifecycleOwner) {
            findNavController().navigateUp()
        }

        viewModel.erro.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Erro: $it ", Toast.LENGTH_LONG).show()
        }
    }
}