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
import androidx.recyclerview.widget.LinearLayoutManager
import com.akatsuki.safeguardpro.R
import com.akatsuki.safeguardpro.databinding.FragmentRelatorioEmprestimoBinding
import com.akatsuki.safeguardpro.service.model.Emprestimo
import com.akatsuki.safeguardpro.view.adapter.EmprestimoAdapter
import com.akatsuki.safeguardpro.viewmodel.EmprestimoViewModel

class RelatorioEmprestimoFragment : Fragment() {
    private val viewModel: EmprestimoViewModel by viewModels()
    private lateinit var adapter: EmprestimoAdapter

    //Configurar o Binding
    private var _binding: FragmentRelatorioEmprestimoBinding? = null
    private val binding: FragmentRelatorioEmprestimoBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRelatorioEmprestimoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = EmprestimoAdapter(viewModel.emprestimoList.value) { emprestimo ->
            val emprestimoBundle = Bundle()
            emprestimoBundle.putInt("emprestimo_id", emprestimo.emprestimo_id)
            arguments = emprestimoBundle
            findNavController().navigate(R.id.relatorioEmprestimoFragment, arguments)
        }

        val recycler = binding.rvListaEmprestimo
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        viewModel.emprestimoList.observe(viewLifecycleOwner) {
            adapter.updateEmprestimo(it)
        }

        viewModel.erro.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Erro Empréstimo: $it", Toast.LENGTH_LONG).show()
            Log.e("Erro Empréstimo", it)
        }

        //Carrega os empréstimos
        viewModel.load()
    }
}