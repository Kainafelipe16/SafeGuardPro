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
import com.akatsuki.safeguardpro.R
import com.akatsuki.safeguardpro.databinding.FragmentLoginBinding
import com.akatsuki.safeguardpro.service.model.Login
import com.akatsuki.safeguardpro.viewmodel.FuncionarioViewModel

class LoginFragment : Fragment() {

    private val viewModelFuncionario: FuncionarioViewModel by viewModels()

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var cpf = ""
        var senha = ""

        binding.btnLogar.setOnClickListener {
            cpf = binding.edtCpf.editableText.toString()
            senha = binding.edtSenha.editableText.toString()

            if (cpf != "" && senha != "") {
                viewModelFuncionario.getFuncionarioByCpf(cpf)
            } else {
                AlertDialog.Builder(requireContext())
                    .setTitle("Dados Inválidos")
                    .setMessage("Digite seu email e sua senha")
                    .setPositiveButton("Ok") { _, _ ->
                    }
                    .show()
            }
        }

        viewModelFuncionario.funcionario.observe(viewLifecycleOwner) {
            if (it.senha == senha && it.cpf == cpf) {
                Login.userConected(it.id, it.cpf, it.admin)
                Log.e("Login","Login = ${Login.userId} - ${Login.userCpf} - ${Login.userAdmin}")
                Toast.makeText(requireContext(), "Login = ${Login.userId} - ${Login.userCpf} - ${Login.userAdmin}", Toast.LENGTH_LONG).show()

                findNavController().navigate(R.id.telaInicialSupervisorFragment)
            } else {
                Toast.makeText(requireContext(), "Usuário ou senha inválidos !!", Toast.LENGTH_LONG).show()
            }
        }

        viewModelFuncionario.erro.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Erro $it", Toast.LENGTH_LONG).show()
            Log.e("Erro ao caadastrar funcionário !!", it)
        }
    }
}