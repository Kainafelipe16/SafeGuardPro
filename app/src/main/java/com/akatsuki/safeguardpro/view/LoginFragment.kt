package com.akatsuki.safeguardpro.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.akatsuki.safeguardpro.R
import com.akatsuki.safeguardpro.databinding.FragmentLoginBinding
import com.akatsuki.safeguardpro.viewmodel.TestViewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogar.setOnClickListener{
            var email = binding.edtemail.editableText.toString()
            var senha = binding.edtsenha.editableText.toString()

            if(email != "" && senha != ""){
                findNavController().navigate(R.id.telaInicialFuncionarioFragment)
            } else{
                AlertDialog.Builder(requireContext())
                    .setTitle("Dados Inválidos")
                    .setMessage("Digite seu email e senha")
                    .setPositiveButton( "Ok"){_,_ ->

                    }
                    .show()
            }


        }
    }
}
